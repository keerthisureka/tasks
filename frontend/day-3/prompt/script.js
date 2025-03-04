function addItem() {
    let skill = prompt("Add your skill here:");
    if (skill) {
        let list = document.getElementById("listItems");
        let listItem = document.createElement("li");
        listItem.textContent = skill;
        list.appendChild(listItem);
    }
}