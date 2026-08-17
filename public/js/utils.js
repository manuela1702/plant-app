const API_URL = 'http://localhost:8080';

const bootstrapClasses = {
    popup: 'card',
    cancelButton: 'btn btn-danger',
    denyButton: 'btn btn-secondary',
    confirmButton: 'btn btn-success'
};


function showLoading() {

    Swal.fire({
        title: 'Loading...',
        text: 'Please wait.',
        allowOutsideClick: false,
        customClass: bootstrapClasses,
        didOpen: () => Swal.showLoading()
    });

}


function showConfirm(msg, callback) {

    Swal.fire({
        title: msg,
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: 'Da',
        cancelButtonText: 'Ne',
        customClass: bootstrapClasses
    }).then(result => {

        if (result.isConfirmed) {

            callback();

            Swal.fire({
                title: 'Success',
                icon: 'success',
                confirmButtonText: 'Ok',
                customClass: bootstrapClasses
            });

        }

    });

}


async function retrieveData(url, callback) {

    try {

        const container = document.querySelector(
            ".container, .container-fluid"
        );

        if (container) {
            container.hidden = true;
        }

        showLoading();

        const rsp = await fetch(API_URL + url);

        if (rsp.status === 404) {

            Swal.fire({
                icon: 'error',
                title: 'Podatak nije pronađen',
                text: 'Traženi podatak ne postoji.',
                customClass: bootstrapClasses
            });

            return;

        }

        const data = await rsp.json();

        callback(data);

        if (container) {
            container.hidden = false;
        }

        Swal.close();

    } catch (e) {

        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: e.message,
            customClass: bootstrapClasses
        });

    }

}


async function showSpecies(id) {

    try {

        const rsp = await fetch(API_URL + `/species/${id}`);

        if (!rsp.ok) {
            throw new Error('Vrsta biljke nije pronađena.');
        }

        const plant = await rsp.json();

        Swal.fire({
            title: plant.name,

            html: `
                <div class="text-start">

                    <img src=".${plant.imageUrl}"
                         alt="${plant.name}"
                         class="img-fluid rounded mb-3 d-block mx-auto"
                         style="width: 250px; height: 180px; object-fit: cover;">

                    <p>
                        <strong>Scientific name:</strong><br>
                        <i>${plant.scientificName ?? '-'}</i>
                    </p>

                    <p>
                        <strong>Sunlight:</strong>
                        ${plant.sunlight}
                    </p>

                    <p>
                        <strong>Watering:</strong>
                        ${plant.watering}
                    </p>

                    <p>
                        <strong>Description:</strong><br>
                        ${plant.description ?? '-'}
                    </p>

                    <hr>

                    <h5 class="mb-3">
                        Recommended Care
                    </h5>

                    <p>
                        <i class="fa-solid fa-droplet me-1"></i>
                        <strong>Watering:</strong>
                        Every ${plant.wateringInterval} days
                    </p>

                    <p>
                        <i class="fa-solid fa-seedling me-1"></i>
                        <strong>Fertilizing:</strong>
                        Every ${plant.fertilizingInterval} days
                    </p>

                    <p>
                        <i class="fa-solid fa-arrows-rotate me-1"></i>
                        <strong>Repotting:</strong>
                        Every ${plant.repottingInterval} days
                    </p>

                </div>
            `,

            confirmButtonText: 'Close',

            customClass: {
                popup: 'card',
                confirmButton: 'btn btn-primary'
            },

            buttonsStyling: false,
            width: 600
        });

    } catch (e) {

        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: e.message,
            customClass: bootstrapClasses
        });

    }

}
async function addPlant(speciesId) {

    const result = await Swal.fire({
        title: 'Add Plant',

        html: `
            <div class="text-start">

                <label for="plant-nickname" class="form-label">
                    Nickname
                </label>

                <input id="plant-nickname"
                       type="text"
                       class="form-control mb-3"
                       placeholder="Enter plant nickname">


                <label for="planting-date" class="form-label">
                    Planting Date
                </label>

                <input id="planting-date"
                       type="date"
                       class="form-control"
                       max="${new Date().toISOString().split('T')[0]}">

            </div>
        `,

        showCancelButton: true,

        confirmButtonText: 'Add',
        cancelButtonText: 'Cancel',

        customClass: {
            popup: 'card',
            confirmButton: 'btn btn-primary',
            cancelButton: 'btn btn-secondary'
        },

        buttonsStyling: false,

        width: 600,

        focusConfirm: false,

        preConfirm: () => {

            const nickname =
                document.getElementById('plant-nickname').value.trim();

            const plantingDate =
                document.getElementById('planting-date').value;

            const today =
                new Date().toISOString().split('T')[0];


            if (!nickname || !plantingDate) {

                Swal.showValidationMessage(
                    'Please fill in all fields.'
                );

                return false;
            }


            if (plantingDate > today) {

                Swal.showValidationMessage(
                    'Planting date cannot be in the future.'
                );

                return false;
            }


            return {
                nickname: nickname,
                plantingDate: plantingDate,

                plantSpecies: {
                    plantSpeciesId: speciesId
                }
            };

        }
    });


    if (!result.isConfirmed) {
        return;
    }


    try {

        const rsp = await fetch(API_URL + '/plants', {

            method: 'POST',

            headers: {
                'Content-Type': 'application/json'
            },

            body: JSON.stringify(result.value)

        });


        if (!rsp.ok) {
            throw new Error('Plant could not be added.');
        }


        await Swal.fire({

            icon: 'success',

            title: 'Plant added',

            text: 'Your plant has been successfully added.',

            confirmButtonText: 'OK',

            customClass: {
                popup: 'card',
                confirmButton: 'btn btn-success'
            },

            buttonsStyling: false

        });


    } catch (e) {

        Swal.fire({

            icon: 'error',

            title: 'Error',

            text: e.message,

            customClass: bootstrapClasses

        });

    }

}
function getLastActivityDate(activities, type, plantingDate) {

    const filtered = activities
        .filter(activity => activity.activityType === type)
        .sort((a, b) =>
            new Date(b.activityDate) - new Date(a.activityDate)
        );

    if (filtered.length > 0) {
        return filtered[0].activityDate;
    }

    return plantingDate;
}


function getPlantStatus(lastDate, interval) {

    const start = new Date(lastDate + 'T00:00:00');

    const dueDate = new Date(start);
    dueDate.setDate(dueDate.getDate() + interval);

    const today = new Date();
    today.setHours(0, 0, 0, 0);

    const millisecondsPerDay = 1000 * 60 * 60 * 24;

    const daysLeft = Math.ceil(
        (dueDate - today) / millisecondsPerDay
    );


    if (daysLeft <= 0) {

        return {
            status: 'danger',
            text: daysLeft === 0
                ? 'Due today'
                : `${Math.abs(daysLeft)} days overdue`,
            due: true
        };

    }


    const warningDays = Math.max(
        1,
        Math.ceil(interval * 0.25)
    );


    if (daysLeft <= warningDays) {

        return {
            status: 'warning',
            text: `In ${daysLeft} days`,
            due: false
        };

    }


    return {
        status: 'success',
        text: `In ${daysLeft} days`,
        due: false
    };
}


function getStatusBadge(status) {

    let textClass = '';

    if (status.status === 'warning') {
        textClass = ' text-dark';
    }

    return `
        <span class="badge bg-${status.status}${textClass}">
            ${status.text}
        </span>
    `;
}
async function showMyPlant(id) {

    try {

        const plantRsp = await fetch(
            API_URL + `/plants/${id}`
        );

        if (!plantRsp.ok) {
            throw new Error('Plant could not be loaded.');
        }

        const plant = await plantRsp.json();


        const activitiesRsp = await fetch(
            API_URL + `/activities/plant/${id}`
        );

        if (!activitiesRsp.ok) {
            throw new Error('Plant activities could not be loaded.');
        }

        const activities = await activitiesRsp.json();

        let activityHistory = '';

        if (activities.length === 0) {

            activityHistory = `
                <p class="text-muted mb-0">
                    No activities yet.
                </p>
            `;

        } else {

            for (let activity of activities) {

                let icon = '';

                switch (activity.activityType) {

                    case 'WATERING':
                        icon = 'fa-droplet';
                        break;

                    case 'FERTILIZING':
                        icon = 'fa-seedling';
                        break;

                    case 'REPOTTING':
                        icon = 'fa-arrows-rotate';
                        break;

                }

                activityHistory += `

                    <div class="border rounded p-2 mb-2">

                      <div class="d-flex justify-content-between align-items-center">

                          <div>

                              <i class="fa-solid ${icon} me-2"></i>

                              <strong>${activity.activityType}</strong>

                              <br>

                              <small>${activity.activityDate}</small>

                          </div>

                          <div>

                              <button class="btn btn-sm btn-outline-primary me-1"
                                      onclick="editActivity(
                                          ${activity.plantActivityId},
                                          '${activity.activityDate}',
                                          '${activity.activityType}',
                                          ${plant.myPlantId}
                                      )">

                                  <i class="fa-solid fa-pen"></i>

                              </button>

                              <button class="btn btn-sm btn-outline-danger"
                                      onclick="deleteActivity(${activity.plantActivityId}, ${plant.myPlantId})">

                                  <i class="fa-solid fa-trash"></i>

                              </button>

                          </div>

                      </div>

                    </div>

                `;

            }

        }


        const lastWatering = getLastActivityDate(
            activities,
            'WATERING',
            plant.plantingDate
        );

        const lastFertilizing = getLastActivityDate(
            activities,
            'FERTILIZING',
            plant.plantingDate
        );

        const lastRepotting = getLastActivityDate(
            activities,
            'REPOTTING',
            plant.plantingDate
        );


        const wateringStatus = getPlantStatus(
            lastWatering,
            plant.plantSpecies.wateringInterval
        );

        const fertilizingStatus = getPlantStatus(
            lastFertilizing,
            plant.plantSpecies.fertilizingInterval
        );

        const repottingStatus = getPlantStatus(
            lastRepotting,
            plant.plantSpecies.repottingInterval
        );


        await Swal.fire({

            title: plant.nickname,

            html: `
                <div class="text-start">

                    <img src=".${plant.plantSpecies.imageUrl}"
                         alt="${plant.plantSpecies.name}"
                         class="img-fluid rounded mb-3 d-block mx-auto"
                         style="width: 250px; height: 180px; object-fit: cover;">


                    <h5 class="mb-3">
                        ${plant.plantSpecies.name}
                    </h5>


                    <p>
                        <strong>Scientific name:</strong><br>
                        <i>${plant.plantSpecies.scientificName ?? '-'}</i>
                    </p>

                    <p>
                        <strong>Sunlight:</strong>
                        ${plant.plantSpecies.sunlight ?? '-'}
                    </p>

                    <p>
                        <strong>General watering:</strong>
                        ${plant.plantSpecies.watering ?? '-'}
                    </p>

                    <p>
                        <strong>Description:</strong><br>
                        ${plant.plantSpecies.description ?? '-'}
                    </p>


                    <hr>


                    <h5 class="mb-3">
                        My Plant
                    </h5>

                    <p>
                        <strong>Nickname:</strong>
                        ${plant.nickname}
                    </p>

                    <p>
                        <strong>Planting date:</strong>
                        ${plant.plantingDate ?? '-'}
                    </p>


                    <hr>


                    <div class="mb-4">

                        <div class="d-flex justify-content-between align-items-center mb-2">

                            <strong>
                                <i class="fa-solid fa-droplet me-1"></i>
                                Watering
                            </strong>

                            ${getStatusBadge(wateringStatus)}

                        </div>

                        <div class="mb-2">
                            Every ${plant.plantSpecies.wateringInterval} days
                        </div>

                        <button type="button"
                                class="btn btn-sm btn-outline-primary"
                                onclick="addActivity(${plant.myPlantId}, 'WATERING')">

                            <i class="fa-solid fa-check me-1"></i>
                            Watered

                        </button>

                    </div>


                    <div class="mb-4">

                        <div class="d-flex justify-content-between align-items-center mb-2">

                            <strong>
                                <i class="fa-solid fa-seedling me-1"></i>
                                Fertilizing
                            </strong>

                            ${getStatusBadge(fertilizingStatus)}

                        </div>

                        <div class="mb-2">
                            Every ${plant.plantSpecies.fertilizingInterval} days
                        </div>

                        <button type="button"
                                class="btn btn-sm btn-outline-primary"
                                onclick="addActivity(${plant.myPlantId}, 'FERTILIZING')">

                            <i class="fa-solid fa-check me-1"></i>
                            Fertilized

                        </button>

                    </div>


                    <div class="mb-2">

                        <div class="d-flex justify-content-between align-items-center mb-2">

                            <strong>
                                <i class="fa-solid fa-arrows-rotate me-1"></i>
                                Repotting
                            </strong>

                            ${getStatusBadge(repottingStatus)}

                        </div>

                        <div class="mb-2">
                            Every ${plant.plantSpecies.repottingInterval} days
                        </div>

                        <button type="button"
                                class="btn btn-sm btn-outline-primary"
                                onclick="addActivity(${plant.myPlantId}, 'REPOTTING')">

                            <i class="fa-solid fa-check me-1"></i>
                            Repotted

                        </button>

                    </div>
                    <hr>

                    <h5 class="mb-3">
                        Activity History
                    </h5>

                    ${activityHistory}

                </div>
            `,

            confirmButtonText: 'Close',

            customClass: {
                popup: 'card',
                confirmButton: 'btn btn-primary'
            },

            buttonsStyling: false,
            width: 600

        });


    } catch (e) {

        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: e.message,
            customClass: bootstrapClasses
        });

    }

}
async function addActivity(plantId, activityType) {

    try {

        const today = new Date();

        const activityDate =
            today.getFullYear() + '-' +
            String(today.getMonth() + 1).padStart(2, '0') + '-' +
            String(today.getDate()).padStart(2, '0');


        const rsp = await fetch(
            API_URL + '/activities',
            {
                method: 'POST',

                headers: {
                    'Content-Type': 'application/json'
                },

                body: JSON.stringify({
                    activityDate: activityDate,
                    activityType: activityType,

                    myPlant: {
                        myPlantId: plantId
                    }
                })
            }
        );


        if (!rsp.ok) {
            throw new Error('Activity could not be saved.');
        }


        Swal.close();


        await Swal.fire({
            icon: 'success',
            title: 'Activity saved',
            confirmButtonText: 'OK',

            customClass: {
                popup: 'card',
                confirmButton: 'btn btn-success'
            },

            buttonsStyling: false
        });


        showMyPlant(plantId);


    } catch (e) {

        Swal.fire({
            icon: 'error',
            title: 'Error',
            text: e.message,
            customClass: bootstrapClasses
        });

    }

}
async function deleteActivity(activityId, plantId) {

    const result = await Swal.fire({

        title: 'Delete activity?',

        text: 'This action cannot be undone.',

        icon: 'warning',

        showCancelButton: true,

        confirmButtonText: 'Delete',

        cancelButtonText: 'Cancel',

        customClass: {
            popup: 'card',
            confirmButton: 'btn btn-danger',
            cancelButton: 'btn btn-secondary'
        },

        buttonsStyling: false

    });

    if (!result.isConfirmed) {
        return;
    }

    try {

        const rsp = await fetch(
            API_URL + `/activities/${activityId}`,
            {
                method: 'DELETE'
            }
        );

        if (!rsp.ok) {
            throw new Error('Activity could not be deleted.');
        }

       await Swal.fire({

           icon: 'success',

           title: 'Activity deleted',

           confirmButtonText: 'OK',

           customClass: {
               popup: 'card',
               confirmButton: 'btn btn-success'
           },

           buttonsStyling: false

       });

       showMyPlant(plantId);

    } catch (e) {

        Swal.fire({

            icon: 'error',

            title: 'Error',

            text: e.message,

            customClass: bootstrapClasses

        });

    }

}
async function editActivity(activityId,
                            activityDate,
                            activityType,
                            plantId) {

    const result = await Swal.fire({

        title: 'Edit Activity',

        html: `

            <div class="text-start">

                <label class="form-label">
                    Activity Date
                </label>

                <input id="activity-date"
                       type="date"
                       class="form-control"
                       value="${activityDate}"
                       max="${new Date().toISOString().split('T')[0]}">

            </div>

        `,

        showCancelButton: true,

        confirmButtonText: 'Save',

        cancelButtonText: 'Cancel',

        customClass: {
            popup: 'card',
            confirmButton: 'btn btn-primary',
            cancelButton: 'btn btn-secondary'
        },

        buttonsStyling: false,

       preConfirm: () => {

           const newDate =
               document.getElementById('activity-date').value;

           const today =
               new Date().toISOString().split('T')[0];

           if (!newDate) {

               Swal.showValidationMessage(
                   'Please select a date.'
               );

               return false;
           }

           if (newDate > today) {

               Swal.showValidationMessage(
                   'Activity date cannot be in the future.'
               );

               return false;
           }

           return newDate;

       }

    });

    if (!result.isConfirmed) {
        return;
    }

    try {

        const rsp = await fetch(
            API_URL + `/activities/${activityId}`,
            {

                method: 'PUT',

                headers: {
                    'Content-Type': 'application/json'
                },

                body: JSON.stringify({

                    activityDate: result.value,

                    activityType: activityType,

                    myPlant: {
                        myPlantId: plantId
                    }

                })

            }
        );

        if (!rsp.ok) {
            throw new Error('Activity could not be updated.');
        }

        await Swal.fire({

            icon: 'success',

            title: 'Activity updated',

            confirmButtonText: 'OK',

            customClass: {
                popup: 'card',
                confirmButton: 'btn btn-success'
            },

            buttonsStyling: false

        });

        showMyPlant(plantId);


    } catch (e) {

        Swal.fire({

            icon: 'error',

            title: 'Error',

            text: e.message,

            customClass: bootstrapClasses

        });

    }

}