/*
function myFunction() {
    var x = document.getElementsByTagName("LI");
    document.getElementById("demo").innerHTML = x[1].innerHTML;
    for (var i in [...x]) {
       x[i].style.color = "green";
    }
}
*/
/*
let sum = (a,b) => {
    var c = a + b;
    return c;
}

console.log(sum(2,3));

let sum_ = (a,b) => a + b;

let sum__ = a => a+3;

let sum___ = () => 3;
*/

/*
function addText() {
        let x = document.getElementsByTagName("LI");
        document.getElementById("demo").innerHTML = x[1].innerHTML;
}

function menuTemp(){
    let ices = document.getElementsByClassName("ice-menu");
    for(let i in [...ices]){
        ices[i].style.color = "blue";
    }
    let hots = document.getElementsByClassName("hot-menu");
    for(let i in [...hots]){
        hots[i].style.color = "red";
    }
}

function removeMenu(){
    let info = document.querySelector("#demo");
    info.innerHTML = "";
}

function menuTempClear(){
    let lis = document.querySelectorAll(".menu-box li");
    lis.forEach(x => x.style.color = "black");
}

function fontSizeUp() {
    let lis = document.querySelectorAll("#meun-ul li");
    lis.forEach((x) => (x.style.fontSize = "30px"));
}

function popupSelected() {
    let demo = document.getElementsByClassName("select-textbox")[0];
    let selectedNum = demo.value;
    console.log(selectedNum);
    alert(getMenuName(selectedNum));
}

function getMenuName(pos) {
    if (pos < 0 || pos > 4) {
        alert("입력이 올바르지 않습니다.");
        return;
    }
    let menuUL = document.getElementById("meun-ul");
    let str = null;
    if (pos == 1) {
        str = menuUL.firstElementChild.innerHTML;
    } else if (pos == 2) {
        str = menuUL.firstElementChild.nextElementSibling.innerHTML;
    } else {
        str = menuUL.lastElementChild.innerHTML;
    }
    return str;
}

var button = document.getElementById('button');
var anchor = document.getElementById('anchor');

console.log(button.href);
console.log(anchor.href);
console.log(button.onclick);
console.log(anchor.onclick);
*/

function myFunction(){
    let header = document.getElementsByTagName("H1")[0];
    let body = document.getElementsByTagName("H1")[1];
    header.setAttribute("class", "header-text");
    body.setAttribute("class", "body-text");
}

var root = document.getElementsByTagName('ol')[0]; //root는 살아있는 객체
var li = document.createElement('li');		// <li><li>
var content = document.createTextNode('게임');      //  ‘게임’
li.id = 'newLi';			
li.className = 'festival';		      //<li class=“festival”><li>	
li.appendChild(content);		      //<li class=“festival”>게임<li>	
root.appendChild(li);
