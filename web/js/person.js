/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var _personID = "";

 $.urlParam = function(name){
        var results = new RegExp('[\?&]' + name + '=([^&#]*)').exec(window.location.href);
        if (results===null){
           return null;
        }
        else{
           return results[1] || 0;
        }
    };

$(document).ready(function(){
    _personID = $.urlParam('id');
    console.log('ready : _personID');
    console.log(_personID);
    showSpinner();
    getPersonInfo();
});


function showSpinner(){
    $('#spinner').show();
}

function hideSpinner(){
    $('#spinner').hide();
}

function getPersonInfo(){
    
    var personID = _personID;
    
    
    if(personID !== undefined && personID !== null && personID !== ""){
        showSpinner();
        $.ajax({
            type: "GET",
            url: "/LOG660-LAB02/webresources/film/getPersonInfo/"+personID,
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            contentType: "application/json",
            dataType: "json",
            data: personID,
            success: function (personObject) {
                if(personObject !== undefined && personObject !== null){
                    console.log("getPersonData : personObject");
                    console.log(personObject);
                    //alert(JSON.stringify(personObject));
                    // $(location).attr('href', data);
                    showPersonData(personObject);
                }
                else{
                    hideSpinner();
                    console.log("ERROR: Person info is null");
                }
            },
            error: function (xhr, status, error) {
                // Mettre les champs en erreur
                hideSpinner();
                alert("ERROR! See console...");
                console.log(xhr.responseText);
                console.log(status);
                console.log(error);
            }
        });
    }
    else{
        hideSpinner();
    }
}

function setFilmInfo(film){
    var title = film.title;
    var duration = film.duration;
    var year = film.year;
    var language = film.language;
    var synopsis = film.synopsis;
    var directorId = film.director.directorId;
    var directorName = film.director.directorName;
    
    $('#title').html(title);
    $('#length').html(duration);
    $('#year').html(year);
    $('#language').html(language);
    $('#synopsis').html(synopsis);
    
    $('#director').html(directorName);
    $('#director').attr("data-directorid",directorId);
    $('#director').attr("onclick","getPersonData('"+directorId+"');");
    $('#director').attr("class","personLink");
    $('#director').attr("title","Cliquer pour afficher les informations pour ce réalisateur");
    
    var actorsDiv = document.getElementById("actors");
    for(var i = 0; i < film.actors.length; i++){
        var actor = film.actors[i];
        var actorId = actor.actorId;
        var actorName = actor.actorName;
        var characterName = actor.characterName;
        
        var newActor = document.createElement("span");
        newActor.innerHTML = actorName + " (" + characterName + ")";
        newActor.setAttribute("data-actorid",actorId);
        newActor.setAttribute("onclick","getPersonData('"+actorId+"');")
        newActor.className = "personLink";
        newActor.setAttribute("title","Cliquer pour afficher les informations pour cet acteur");
        
        actorsDiv.appendChild(newActor);
        
        if(i < film.actors.length - 1){
            actorsDiv.innerHTML += ", ";
        }
    }
    
    var genresDiv = document.getElementById("genres");
    for(var i = 0; i < film.genres.length; i++){
        var genre = film.genres[i];
        genresDiv.innerHTML += genre;
        if(i < film.genres.length - 1){
            genresDiv.innerHTML += ", ";
        }
    }
    
    var screenwritersDiv = document.getElementById("screenwriters");
    for(var i = 0; i < film.screenwriters.length; i++){
        var screenwriter = film.screenwriters[i];
        screenwritersDiv.innerHTML += screenwriter;
        if(i < film.screenwriters.length - 1){
            screenwritersDiv.innerHTML += ", ";
        }
    }
    
    var countriesDiv = document.getElementById("countries");
    for(var i = 0; i < film.countries.length; i++){
        var country = film.countries[i];
        countriesDiv.innerHTML += country;
        if(i < film.countries.length - 1){
            countriesDiv.innerHTML += ", ";
        }
    }
    
    if(!film.filmCopiesLeft){
        turnOffRentMovieBtn("Il n'y a plus d'exemplaires dispnibles");
    }
    
    fetchRating();
    fetchSuggestedFilms();
    $('#filmContent').show();
}

function showPersonData(person){
    if(person.name === undefined || person.name === null || person.name === ""){
         $('#personName').html("(non spécifié)");
    }
    else{
        $('#personName').html(person.name);
    }
    
    
    if(person.dateOfBirth === undefined || person.dateOfBirth === null || person.dateOfBirth === ""){
         $('#personDateOfBirth').html("(non spécifiée)");
    }
    else{
        $('#personDateOfBirth').html(person.dateOfBirth);
    }
    
    if(person.placeOfBirth === undefined || person.placeOfBirth === null || person.placeOfBirth === ""){
         $('#personPlaceOfBirth').html("(non spécifié)");
    }
    else{
        $('#personPlaceOfBirth').html(person.placeOfBirth);
    }

    if(person.biography === undefined || person.biography === null || person.biography === ""){
         $('#personBiography').html("(aucune biographie trouvée)");
    }
    else{
        $('#personBiography').html(person.biography);
    }
    hideSpinner();
}