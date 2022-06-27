window.onload = () => {
    let lis = document.querySelectorAll("li");
    //lis.forEach((li) => li.addEventListener("click", toggleList));
    let ul = document.querySelector("#sampleList");
    ul.addEventListener("click", toggleList);

    let addBtn = document.querySelector("#addBtn");
    addBtn.addEventListener("click", addList);
};
function toggleList(event) {
    let clickedLi = document.getElementById(event.target.id);
    clickedLi.classList.toggle("remove");
}
function addList() {
    let sampleList = document.getElementById("#sampleList");
    let listLen = sampleList.children.length;
    let newLi = document.createElement("li");
    newLi.setAttribute("id", "list" + listLen + 1);
    newLi.appendChild(document.createTextNode("기존 리스트 " + (listLen + 1)));
    sampleList.appendChild(newLi);
}