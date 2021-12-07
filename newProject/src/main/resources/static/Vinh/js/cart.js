// Function Tăng - Giảm số lượng
const minusButtonElement = document.getElementsByClassName('minus-btn');
console.log("minusButtonElement: ",minusButtonElement.length);
const indexMaxElement =document.getElementsByClassName('cart__row');
console.log("cartRows: ",indexMaxElement.length);

let check=0;
let check1=0;
let check2=0;
let check3=0;
let check4=0;
var maxtotal=0;
var submitTotalMax= document.getElementById('grand__total');
var submitTotalMax1= document.getElementById('grand__total1');
var submitQuantily = document.getElementById('grand__quantily');
//lay tong tien
for (let i = 1;i<=indexMaxElement.length; i++) {
    var number = document.getElementById('totalMoney'+i);
    var input = parseInt(number.value);
    console.log(number +" "+input+" " + typeof(number));
    maxtotal+=input;
}
submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
submitTotalMax1.value = maxtotal
//lay tong so luong
var maxquantily=0;
for (let i = 1;i<=indexMaxElement.length; i++) {
    var quantily = document.getElementById('submitQuantily'+i);
    var inputQuantily = parseInt(quantily.value);
    console.log(quantily +" "+input+" " + typeof(number));
    maxquantily+=inputQuantily;
}
submitQuantily.value = maxquantily;
for(let index = 1;index<=indexMaxElement.length;index++){
    //index1
    while(index===1){
        if(index>=check){
            var quantityAction1=parseInt(document.getElementById('submitQuantily'+index).value);
            var totalAction1=parseInt(document.getElementById('totalMoney'+index).value);
            var priceTotalElement1 = parseInt(document.getElementById('product__price'+index).value);
            var maxquantily1 = parseInt(document.getElementById('maxquantily'+index).value);
        }
        check=index+1;
        break;
    }
    function handleDescrease1() {
        if(quantityAction1>1){
            quantityAction1 -=1;
            totalAction1 = totalAction1 - priceTotalElement1;
            maxtotal -= priceTotalElement1;
            console.log(quantityAction1);
            document.getElementById('submitQuantily1').value = quantityAction1;
            document.getElementById('totalMoneySubmit1').innerText = totalAction1.toLocaleString() + " " + 'VND';
            submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
            submitTotalMax1.value = maxtotal;
            maxquantily -=1;
            submitQuantily.value = maxquantily;
        }else{
            alert("không được giảm quá 1");
        }
    }
    function handleIncrease1(){
        console.log(maxquantily1)
        if(quantityAction1<maxquantily1){
        quantityAction1 +=1;
        console.log(quantityAction1);
        totalAction1 = totalAction1 + priceTotalElement1
        maxtotal += priceTotalElement1;
        document.getElementById('submitQuantily1').value = quantityAction1;
        document.getElementById('totalMoneySubmit1').innerText = totalAction1.toLocaleString() + " " + 'VND';
        submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
            submitTotalMax1.value = maxtotal
            maxquantily +=1;
            submitQuantily.value = maxquantily;
        }else{
            alert(" Da Vuot qua so luong")
        }
    }
    while(index===2){
        if(index>=check1){
            var quantityAction2=parseInt(document.getElementById('submitQuantily'+index).value);
            var totalAction2=parseInt(document.getElementById('totalMoney'+index).value);
            var priceTotalElement2 = parseInt(document.getElementById('product__price'+index).value);
            var maxquantily2 = parseInt(document.getElementById('maxquantily'+index).value);
        }
        check1=index+1;
        break;
    }
    function handleDescrease2() {
        if(quantityAction2>1){
            quantityAction2 -=1;
            totalAction2 = totalAction2 - priceTotalElement2;
            maxtotal -= priceTotalElement2;
            console.log(quantityAction2);
            document.getElementById('submitQuantily2').value = quantityAction2;
            document.getElementById('totalMoneySubmit2').innerText = totalAction2.toLocaleString() + " " + 'VND';
            submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
submitTotalMax1.value = maxtotal
            maxquantily -=1;
            submitQuantily.value = maxquantily;
        }else{
            alert("không được giảm quá 1");
        }

    }
    function handleIncrease2(){
        console.log(maxquantily2)
        if(quantityAction2<maxquantily2){
            quantityAction2 +=1;
            totalAction2 = totalAction2 + priceTotalElement2;
            maxtotal += priceTotalElement2;
            console.log(quantityAction2);
            document.getElementById('submitQuantily2').value = quantityAction2;
            document.getElementById('totalMoneySubmit2').innerText = totalAction2.toLocaleString() + " " + 'VND';
            submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
submitTotalMax1.value = maxtotal
            maxquantily +=1;
            submitQuantily.value = maxquantily;
        } else{
            alert(" Da Vuot qua so luong")
        }
    }
    while(index===3){
        if(index>=check2){
            var quantityAction3=parseInt(document.getElementById('submitQuantily'+index).value);
            var totalAction3=parseInt(document.getElementById('totalMoney'+index).value);
            var priceTotalElement3 = parseInt(document.getElementById('product__price'+index).value);
            var maxquantily3 = parseInt(document.getElementById('maxquantily'+index).value);
        }
        check2=index+1;
        break;
    }
    function handleDescrease3() {
        if(quantityAction3>1){
            quantityAction3 -=1;
            totalAction3 = totalAction3 - priceTotalElement3;
            maxtotal -= priceTotalElement3;
            console.log(quantityAction3);
            document.getElementById('submitQuantily3').value = quantityAction3;
            document.getElementById('totalMoneySubmit3').innerText = totalAction3.toLocaleString() + " " + 'VND';
            submitTotalMax.innerHTML = maxtotal.toLocaleString() + " " + 'VND';
            maxquantily -=1;
            submitQuantily.innerHTML = maxquantily;
        }else{
            alert("không được giảm quá 1");
        }
    }
    function handleIncrease3(){
        if(quantityAction3<maxquantily3){
        quantityAction3 +=1;
        totalAction3 = totalAction3 + priceTotalElement3;
        console.log(quantityAction3);
        maxtotal += priceTotalElement3;
        document.getElementById('submitQuantily3').value = quantityAction3;
        document.getElementById('totalMoneySubmit3').innerText = totalAction3.toLocaleString() + " " + 'VND';
            submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
submitTotalMax1.value = maxtotal
            maxquantily +=1;
            submitQuantily.value = maxquantily;
        }else{
            alert(" Da Vuot qua so luong")
        }
    }
    while(index===4){
        if(index>=check3){
            var quantityAction4=parseInt(document.getElementById('submitQuantily'+index).value);
            var totalAction4=parseInt(document.getElementById('totalMoney'+index).value);
            var priceTotalElement4 = parseInt(document.getElementById('product__price'+index).value);
            var maxquantily4 = parseInt(document.getElementById('maxquantily'+index).value);
        }
        check3=index+1;
        break;
    }
    function handleDescrease4() {
        if(quantityAction4>1){
            quantityAction4 -=1;
            totalAction4 = totalAction4 - priceTotalElement4;
            console.log(quantityAction4);
            maxtotal -= priceTotalElement4;
            document.getElementById('submitQuantily4').value = quantityAction4;
            document.getElementById('totalMoneySubmit4').innerText = totalAction4.toLocaleString() + " " + 'VND';
            submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
submitTotalMax1.value = maxtotal
            maxquantily -=1;
            submitQuantily.value = maxquantily;
        }else{
            alert("không được giảm quá 1");
        }
    }
    function handleIncrease4(){
        if(quantityAction4<maxquantily4){
        quantityAction4 +=1;
        totalAction4 = totalAction4 + priceTotalElement4;
        console.log(quantityAction4);
            maxtotal += priceTotalElement4;
        document.getElementById('submitQuantily4').value = quantityAction4;
        document.getElementById('totalMoneySubmit4').innerText = totalAction4.toLocaleString() + " " + 'VND';
            submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
submitTotalMax1.value = maxtotal
            maxquantily +=1;
            submitQuantily.value = maxquantily;
        }else{
            alert(" Da Vuot qua so luong")
        }
    }
    while(index===5){
        if(index>=check4){
            var quantityAction5=parseInt(document.getElementById('submitQuantily'+index).value);
            var totalAction5=parseInt(document.getElementById('totalMoney'+index).value);
            var priceTotalElement5 = parseInt(document.getElementById('product__price'+index).value);
            var maxquantily4 = parseInt(document.getElementById('maxquantily'+index).value);
        }
        check4=index+1;
        break;
    }
    function handleDescrease5() {
        if(quantityAction5>1){
            quantityAction5 -=1;
            totalAction5 = totalAction5 - priceTotalElement5;
            console.log(quantityAction5);
            maxtotal -= priceTotalElement5;
            document.getElementById('submitQuantily5').value = quantityAction5;
            document.getElementById('totalMoneySubmit5').innerText = totalAction5.toLocaleString() + " " + 'VND';
            submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
submitTotalMax1.value = maxtotal
            maxquantily -=1;
            submitQuantily.value = maxquantily;
        }else{
            alert("không được giảm quá 1");
        }
    }
    function handleIncrease5(){
        if(quantityAction4<maxquantily4){
        quantityAction5 +=1;
        totalAction5 = totalAction5 + priceTotalElement5;
        console.log(quantityAction5);
            maxtotal += priceTotalElement5;
        document.getElementById('submitQuantily5').value = quantityAction5;
        document.getElementById('totalMoneySubmit5').innerText = totalAction5.toLocaleString() + " " + 'VND';
            submitTotalMax.value = maxtotal.toLocaleString() + " " + 'VND';  
submitTotalMax1.value = maxtotal
            maxquantily +=1;
            submitQuantily.value = maxquantily;
        }else{
            alert(" Da Vuot qua so luong")
        }
    }
}




