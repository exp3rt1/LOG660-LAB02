/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function(){
    if(window.location.hash.substring(1) === "error") {
        $('#errorBox').show();
    }
    $('#boutonLogin').click(function(){
        var courriel = $('#clientCourriel').val();
        var motPasse = $('#clientMotPasse').val();
        console.log(courriel + ": " + motPasse);
    });
});
