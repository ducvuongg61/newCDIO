var slideIndex = 1;
showSlides(slideIndex);

function plusSlides(n) {
    showSlides(slideIndex += n);
}

function currentSlide(n) {
    showSlides(slideIndex = n);
}


function showSlides(n) {
    var i;
    var slides = document.getElementsByClassName("mySlides");
    var dots = document.getElementsByClassName("dot");
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

function minusBtn(){
    let price = parseInt(document.getElementById("input-level").value);
    price -= 1000;
    if(price < 0){
        price = 0;
    }
    document.getElementById("input-level").value = price;
}

function plusBtn(){
    let price = parseInt(document.getElementById("input-level").value);
    price += 1000;
    document.getElementById("input-level").value = price;
}


