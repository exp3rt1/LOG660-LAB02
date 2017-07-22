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
    showSpinner();
    getPersonData(_personID);
});


function showSpinner(){
    $('#spinner').show();
}

function hideSpinner(){
    $('#spinner').hide();
}

function getFilmInfo(){
    
    var id = _filmID;
    
    if(id !== undefined && id !== null && id !== ""){     
        $.ajax({
            type: "GET",
            url: "/LOG660-LAB02/webresources/film/getFilmInfo/"+id,
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            contentType: "application/json",
            dataType: "json",
            data: id,
            success: function (data) {
                hideSpinner();
                setFilmInfo(data);
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


function getPersonData(personID){
    
    /*var person = new Object();
    person.personID = personID;*/
    
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
                    //alert(JSON.stringify(personObject));
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
    // showPersonModal();
}


function showPersonModal(){
    $('#personModal').show();
}

function hidePersonModal(){
    $('#personModal').hide();
    $('#personName').html('');
    $('#personDateOfBirth').html('');
    $('#personPlaceOfBirth').html('');
    $('#personBiography').html('');
}

function tryToClosePersonModal(event){
    if(event.target === document.getElementById("personModal")){hidePersonModal();}
}


function turnOffRentMovieBtn(offMsg){
    var rentMovieBtn = document.getElementById("rentMovieBtn");
    rentMovieBtn.className = "rentMovieBtnOff";
    rentMovieBtn.disabled = true;
    if(offMsg === undefined || offMsg === null || offMsg === ""){
        offMsg = "Vous ne pouver pas louer ce film";
    }
    rentMovieBtn.value = offMsg;
}


function rentMovie(){
    
    var id = _filmID;
    
    var rental = new Object();
    rental.filmID = id;
    
    if(id !== undefined && id !== null && id !== ""){  
        showSpinner();
        $.ajax({
            type: "GET",
            url: "/LOG660-LAB02/webresources/film/louerFilm/"+id,
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            contentType: "application/json",
            dataType: "json",
            data: rental,
            success: function (result) {
                hideSpinner();
                if(result.success === true){
                    if(result.noMoreCopies === true){
                        turnOffRentMovieBtn("Il n'y a plus d'exemplaires dispnibles");
                    }
                    alert(result.message);
                }
                else if(result.success === false){
                    if(result.noMoreCopies === true){
                        turnOffRentMovieBtn("Il n'y a plus d'exemplaires dispnibles");
                    }
                    alert(result.message);
                }
                else {
                    alert("FAIL");
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


function fetchRating(){
    var id = _filmID;
    
    if(id !== undefined && id !== null && id !== ""){     
        $.ajax({
            type: "GET",
            url: "/LOG660-LAB02/webresources/film/getFilmRating/"+id,
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            contentType: "application/json",
            dataType: "json",
            data: id,
            success: function (data) {
                hideSpinner();
                var filmRating = data.rating;
                if(filmRating === null){filmRating = "0";}
                $('#starRating').barrating({
                    theme: 'fontawesome-stars-o',
                    initialRating: filmRating,
                    showSelectedRating: true,
                    hoverState: false,
                    readonly: true,
                    silent: true,
                    deselectable: false
                });
                if(filmRating !== "0"){
                    $('#filmRating').html(filmRating + " / 5");
                }
                else{
                    $('#filmRating').html("aucune évaluation");
                }
                //alert(filmRating);
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

function fetchSuggestedFilms(){
    var id = _filmID;
    
    if(id !== undefined && id !== null && id !== ""){     
        $.ajax({
            type: "GET",
            url: "/LOG660-LAB02/webresources/film/getSuggestedFilms/"+id,
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            contentType: "application/json",
            dataType: "json",
            data: id,
            success: function (data) {
                hideSpinner();
                var suggFilmsJSON = data.suggestedFilms;
                var suggestedFilms = document.getElementById("suggestedFilms");
                if(suggFilmsJSON.length === 0){
                    var suggestedFilm = document.createElement("div");
                    suggestedFilm.innerHTML = "(aucune suggestion)";
                    suggestedFilm.style = "text-align: center;";
                    suggestedFilms.appendChild(suggestedFilm);
                    return;
                }
                for(var i = 0; i<suggFilmsJSON.length; i++){
                    var suggFilmId = suggFilmsJSON[i].id;
                    var suggFilmTitle = suggFilmsJSON[i].title;
                    var suggFilmYear = suggFilmsJSON[i].year;
                    var suggestedFilm = document.createElement("div");
                    suggestedFilm.className = "suggestedFilm";
                    suggestedFilm.innerHTML = suggFilmTitle + " (" + suggFilmYear + ")";
                    
                    var url = window.location.href;
                    url = setUrlParameter(url, "id", suggFilmId);
                    
                    suggestedFilm.setAttribute("onclick","window.location.href = '"+url+"';");
                    suggestedFilms.appendChild(suggestedFilm);
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

function setUrlParameter(url, paramName, paramValue) {
    if (url.indexOf(paramName + "=") >= 0) {
            var prefix = url.substring(0, url.indexOf(paramName));
            var suffix = url.substring(url.indexOf(paramName));
            suffix = suffix.substring(suffix.indexOf("=") + 1);
            suffix = (suffix.indexOf("&") >= 0) ? suffix.substring(suffix
                            .indexOf("&")) : "";
            url = prefix + paramName + "=" + paramValue + suffix;
    } else {
            if (url.indexOf("?") < 0)
                    url += "?" + paramName + "=" + paramValue;
            else
                    url += "&" + paramName + "=" + paramValue;
    }
    return url;
}

function afficherPersonnes(id) {
    showSpinner();
    if(id !== undefined && id !== null && id !== ""){     
        $.ajax({
            type: "GET",
            url: "/LOG660-LAB02/webresources/film/afficherPersonne/"+id,
            headers: { 
                'Accept': 'text/html',
                'Content-Type': 'application/json' 
            },
            contentType: "application/json",
            success: function (data) {
                hideSpinner();
                $(location).attr('href', data);
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