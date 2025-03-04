let name = document.getElementById("name");
let email = document.getElementById("email");
let msg = document.getElementById("msg");

name.value = "Keerthi";
email.value = "abc@gmail.com";
msg.value = "Your message here...";

let sendMsgBtn = document.getElementById("send-msg");
sendMsgBtn.addEventListener('click', () => {
    name.value = "";
    email.value = "";
    msg.value = "";

    alert("Message Sent!");
});