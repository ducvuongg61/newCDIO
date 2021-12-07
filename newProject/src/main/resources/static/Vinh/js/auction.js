
function handleIncreasePrice(){
    let total = parseInt(document.getElementById('input-level').value);
    total += 10000;
    console.log("total: ",total + "---- " + "typeOf total:",typeof (total))
    document.getElementById('input-level').value=total;
}


function handleDecreasePrice(){
    let total = parseInt(document.getElementById('input-level').value);
    total -= 10000;
    console.log("total: ",total + " --- " + "typeOf total:",typeof (total))
    document.getElementById('input-level').value=total;
}
