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
            window.location.replace("profile")
            console.log("Profile image deleted successfully")
        },
        error: function () {
            alert("Something went wrong. Please try again")
        }
    });
}

