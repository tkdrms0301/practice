function openNav() {
    document.getElementById("mySidenav").style.width = "250px";
}
  
function closeNav() {
    document.getElementById("mySidenav").style.width = "0";
}

let slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
  showSlides(slideIndex += n);
}

function showSlides(n) {
  let i;
  let slides = document.getElementsByClassName("mySlides");
  let dots = document.getElementsByClassName("dot");
  if (n > slides.length) {slideIndex = 1}    
  if (n < 1) {slideIndex = slides.length}
  for (i = 0; i < slides.length; i++) {
    slides[i].style.display = "none";  
  }
  for (i = 0; i < dots.length; i++) {
    dots[i].className = dots[i].className.replace(" active", "");
  }
  slides[slideIndex-1].style.display = "block";  
  dots[slideIndex-1].className += " active";
}

function accordion(){
  var acc = document.getElementsByClassName("accordion");
  
  for (var i = 0; i < acc.length; i++) {
    acc[i].onclick = function(){
      this.classList.toggle("active");
      for(var j = 0; j < acc.length; j++){
        if(acc[j] !== this)
          acc[j].nextElementSibling.style.display = "none";
      }
      var panel = this.nextElementSibling;
      console.log(this);
      if (panel.style.display === "block") {
        panel.style.display = "none";
      } else {
        panel.style.display = "block";
      }
    }
  }
}

var input = 0;

function addList() {
  let selectList = document.getElementById("selectBox");
  let result = selectList.options[selectList.selectedIndex].text;
  
  let div = document.createElement("div");
  let li = document.createElement("li");
  let li_input = document.createElement("input");
  let li_plus_button = document.createElement("button");
  let li_minus_button = document.createElement("button");
  let li_cancel_button = document.createElement("button");
  let li_cost_value = document.createElement("span");
  let li_cost = document.createElement("div");
  
  li_plus_button.append(document.createTextNode("+"));
  li_minus_button.append(document.createTextNode("-"));
  li_cancel_button.append(document.createTextNode("x"));
  li_cost_value.append(document.createTextNode(0));
  li_cost.append(document.createTextNode("원"));

  div.setAttribute("class", "select_object");
  li.setAttribute("class", "select_object_li");
  li_input.setAttribute("class", "select_object_li");
  li_input.setAttribute("id", "select_object" + selectList.selectedIndex);
  
  li_input.setAttribute("value", "1");

  li_plus_button.setAttribute("class", "select_object_li");
  li_plus_button.setAttribute("onclick", "plus_object(" + selectList.selectedIndex + ")");
  

  li_minus_button.setAttribute("class", "select_object_li");
  li_minus_button.setAttribute("onclick", "minus_object(" + selectList.selectedIndex + ")");

  li_cancel_button.setAttribute("class", "select_object_li");
  li_cancel_button.setAttribute("onclick", "remove_object(" + selectList.selectedIndex + ")");

  li_cost_value.setAttribute("id", "value" + selectList.selectedIndex);

  li_cost.setAttribute("class", "select_object_li");

  div.appendChild(li);
  div.appendChild(li_input);
  div.appendChild(li_plus_button);
  div.appendChild(li_minus_button);
  div.appendChild(li_cancel_button);
  div.appendChild(li_cost_value);
  div.appendChild(li_cost);

  li.appendChild(document.createTextNode(result));
  let addList = document.getElementById("addList");
  addList.appendChild(div);
}

function cal(n, num){
  if(n === 1){
    document.getElementById("value" + n).innerText = num.value * 29800;
  }else if( n === 2){
    document.getElementById("value" + n).innerText = num.value * 39800;
  }else if( n === 3){
    document.getElementById("value" + n).innerText = num.value * 49800;
  }
}

function cal_result(){
  let result = 0;
  for(var i = 1; i <= 3; i++){
    if(document.getElementById("value" + i))
      result += parseInt(document.getElementById("value" + i).innerText);
  }
  document.getElementById("result_value").innerText = result;
}

function plus_object(n){
  var plus = document.getElementById("select_object" + n);
  plus.value++;
  cal(n, plus);
  cal_result();
}

function minus_object(n){
  var minus = document.getElementById("select_object" + n);
  if(minus.value > 0)
    minus.value--;
  cal(n, minus);
  cal_result();
}

function remove_object(n){
  var remove_object = document.getElementById("select_object" + n).parentElement;
  remove_object.remove();
}