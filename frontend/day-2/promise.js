// Promise
function delayExecution(printMessage, timer, msg) {
    const promise = new Promise((resolve, reject) => {
        setTimeout(() => resolve(printMessage(msg)), timer);
    });

    promise
        .then((message) => console.log(message))
        .catch((error) => console.log(error))
        .finally(() => console.log("Exiting promise!"));
    console.log("Outside promise!");
}

function printMessage(msg) {
    return msg;
}

delayExecution(printMessage, 3000, "3 seconds completed!");
console.log("Outside function!");