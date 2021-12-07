
function handleLoadImage(img) {
    const lazyImage = img;
    console.log("lazyImage: ",lazyImage);
    const url = lazyImage.getAttribute('lazy-src');

    lazyImage.setAttribute('src', url);


    lazyImage.classList.add("fadeIn")



}



function ready() {
    const thumbnailElement = document.querySelectorAll('.thumbnail');

    if ('IntersectionObserver' in window) {
        const lazyImgs = document.querySelectorAll('[lazy-src]')    // lấy lazy-src
        console.log("lazyImgs: ",lazyImgs);

        let observer = new IntersectionObserver((entries) => {
            entries.forEach((entryItem) => {
                // console.log("entryItem: ",entryItem.target)
                if (entryItem.isIntersecting) {
                    handleLoadImage(entryItem.target)  // entryItem.target lấy thẻ img của các item

                }
            })

        });

        lazyImgs.forEach((item) => {
            observer.observe(item)
        })
    } else {
        // do something else
    }
}

document.addEventListener('DOMContentLoaded', ready)