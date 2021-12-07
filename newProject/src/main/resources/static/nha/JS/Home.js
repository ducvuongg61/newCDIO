const arrayImage = [
    'k1.jpg',
    'k2.jpg',
]

var index = 0;

let slider__img = document.getElementById('img');

function handlePrev() {
    if (index <= 0) {
        return index = arrayImage.length;
    } else {
        index--;
    }
    return slider__img.setAttribute('src', "images/" + arrayImage[index]);
}

function handleNext() {
    if (index === arrayImage.length - 1) {
        return index = -1;
    } else {
        index++;
    }
    console.log("length Images: ", arrayImage.length - 1)
    return slider__img.setAttribute('src', "images/" + arrayImage[index]);
}

document.getElementsByClassName('.carousel').carousel({
    interval:2000
})