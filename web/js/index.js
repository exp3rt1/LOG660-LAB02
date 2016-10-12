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
            type: "POST",
            url: "/LOG660-LAB02/webresources/client/login",
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            contentType: "application/json",
            dataType: "json",
            data: JSON.stringify({courriel: courriel, motDePasse: motPasse}),
            success: function (data) {
                // redirection sur rechercheFilm
                console.log(data);
            },
            error: function (xhr, status, error) {
                // Mettre les champs en erreur
                alert(xhr.responseText);
                alert(status);
                alert(error);
            }
        });
    });
});
