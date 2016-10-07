/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){    
    $('#boutonLogin').click(function(){
        var courriel = $('#clientCourriel').val();
        var motPasse = $('#clientMotPasse').val();
        console.log(courriel + ": " + motPasse);
        
        $.ajax({
            type: "GET",
            url: "/LOG660-LAB02/client/login",
            dataType: "json",
            success: function (data) {
                console.log(data);
            },
            error: function (xhr) {
                alert(xhr.responseText);
            }
        });
    });
});
