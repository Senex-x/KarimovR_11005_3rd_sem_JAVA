$(document).ready(function () {
    $("#form-file-upload").change(function () {
        $("#form-submit").slideDown()
        $("#form-status").text("Image selected successfully")
    })

    $(".button-delete-profile-image").click(function () {
        deleteProfileImage()
    })
})

function deleteProfileImage() {
    $.ajax({
        url: '/delete-image',
        type: 'POST',
        success: function () {
            $("#form-status").text("Image deleted successfully")
            console.log("Profile image deleted successfully")
        },
        error: function () {
            alert("Something went wrong. Please try again")
        }
    });
}

