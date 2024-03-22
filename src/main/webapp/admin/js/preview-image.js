function displayImage(input) {
    if (input.files && input.files[0]) {
        var reader = new FileReader();

        reader.onload = function(e) {
            document.getElementById('previewImage').src = e.target.result;
            document.getElementById('previewImage').style.display = 'block';
            document.querySelector('.tm-upload-icon').style.display = 'none'; 
        }

        reader.readAsDataURL(input.files[0]);
    }
}

