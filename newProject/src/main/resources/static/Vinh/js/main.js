
/*
   1> Cách viết  truyền tham số bên băng ArrowFunction bên Js

    thumbnailElement[i].onclick = (e) => {            (Es6)
     return handleChangeImage(e.target.src)
    }

  2> Cách viết truyền tham số bằng ArrowFunction bên Reactjs
  onClick={(e) =>handleChangeImage(e.target.src)}

*/


let thumbnailElement = document.getElementsByClassName('thumbnail');



// Cach 1:   truyền tham số đầu vào bằng arrowFunction Js

function handleChangeImage(path) {
    document.getElementById('image').src = path
}

for(let i=0; i<thumbnailElement.length; i++){
    thumbnailElement[i].onclick = (e) => {
        return handleChangeImage(e.target.src)
    }
}




/* Cach 2 : kieu viet khac

function handleChangeImage(e) {
  let path = e.target.src;
  document.getElementById('image').src = path
}


for (let i = 0; i < thumbnailElement.length; i++) {
  thumbnailElement[i].onclick = handleChangeImage
}

*/




















































