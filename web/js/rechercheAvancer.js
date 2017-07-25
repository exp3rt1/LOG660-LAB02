
var today = new Date();
var minDate = 1900;
var maxDate = today.getFullYear();  


$(document).ready(function(){
    
    hideSpinner();
    
    setDatesInDateSelect();
    getAllFilmGenres();
    getAllFilmCountries();
    getAllFilmLanguages();
    
    $('#listeFilms').DataTable({
                "bFilter": false,
                "bJQueryUI": true,
                "bProcessing": true,
                "bInfo": false,
                "bLengthChange": false,
                "bPaginate": true,
                "pageLength": 20,
                "pagingType": "simple_numbers",
                "dom": 'lrtip',
                "language": {
                    "emptyTable": "Aucun film trouvé"
                }
    });
    
    $('#rechercheBouton').click(function(){
        filmSearch();
    });
    
    $('#rechercheAvanceeBouton').click(function(){
        advancedFilmSearch();
    });
});

function filmSearch(){
    var rechercheFilms = new Object();
    var titre = $('#titre').val();
    if(titre !== undefined && titre !== null && titre !== ""){
        rechercheFilms.titres = titre.split(',');
        sendToWebService(rechercheFilms);
    }
    else{
        // GG
    } 
}

function advancedFilmSearch(){
    
    var rechercheFilms = new Object();
    
    var titres = $('#titres').val();
    var realisateurs = $('#realisateurs').val();
    var acteurs = $('#acteurs').val();
    var anneesSortie = $('#anneesSortie').val();
    var pays = $('#pays').val();
    var languesOriginales = $('#languesOriginales').val();
    var genres = $('#genres').val();
    
    if(titres !== undefined && titres !== null && titres !== ""){
        rechercheFilms.titres = titres.split(',');
    }
    if(realisateurs !== undefined && realisateurs !== null && realisateurs !== ""){
        rechercheFilms.realisateurs = realisateurs.split(',');
    }
    if(acteurs !== undefined && acteurs !== null && acteurs !== ""){
        rechercheFilms.acteurs = acteurs.split(',');
    }
    if(anneesSortie !== undefined && anneesSortie !== null && anneesSortie !== ""){
        //rechercheFilms.anneesSortie = anneesSortie.split(',');
        rechercheFilms.anneesSortie = anneesSortie;
    }
    if(pays !== undefined && pays !== null && pays !== ""){
        //rechercheFilms.pays = pays.split(',');
        rechercheFilms.pays = pays;
    }
    if(languesOriginales !== undefined && languesOriginales !== null && languesOriginales !== ""){
        //rechercheFilms.languesOriginales = languesOriginales.split(',');
        rechercheFilms.languesOriginales = languesOriginales;
    }
    if(genres !== undefined && genres !== null && genres !== ""){
        //rechercheFilms.genres = genres.split(',');
        rechercheFilms.genres = genres;
    }
    
    var intervallesAnnees = [];
    
    $('.dateInterval').each(function(loopIndex, intervalDiv) {
        var startDate = $(intervalDiv).find('#debutAnnee').val();
        var endDate = $(intervalDiv).find('#finAnnee').val();
        var startIsNull = (startDate === undefined || startDate === null || startDate === "");
        var endIsNull = (endDate === undefined || endDate === null || endDate === "");
        if(!startIsNull && endIsNull){
            endDate = '99999';
            intervallesAnnees.push(startDate+","+endDate);
        }
        else if(startIsNull && !endIsNull){
            startDate = '0';
            intervallesAnnees.push(startDate+","+endDate);
        }
        else if(!startIsNull && !endIsNull){
            intervallesAnnees.push(startDate+","+endDate);
        }
        else {
            // do nothing
        }
    });
    
    if(intervallesAnnees.length > 0){
        rechercheFilms.intervallesAnnees = intervallesAnnees;
    }
    
    sendToWebService(rechercheFilms);
}


function setDatesInDateSelect() {
    var dates = document.getElementById('anneesSortie');
    //var firstOption = document.createElement('option');
    //firstOption.value = '';
    //firstOption.innerHTML = 'Choisir une date...';
    //dates.appendChild(firstOption);
    for (var i = minDate; i<=maxDate; i++){
        var opt = document.createElement('option');
        opt.value = i;
        opt.innerHTML = i;
        dates.appendChild(opt);
    }
    //$('#anneesSortie').selectpicker('refresh');
}

function addDateInterval(){
    var dateDiv = document.getElementById("dateDiv");
   
    var newIntervalDiv = document.createElement("div");
    newIntervalDiv.className = "dateInterval";
    
    //'btn-group bootstrap-select show-tick'
      
    var debutLabel = document.createElement('label');
    debutLabel.innerHTML = "Année de début :";
    debutLabel.style = '';
    var debut = document.createElement('select');
    debut.id = "debutAnnee";
    debut.className = "selectpicker selectTD";
    debut.style = 'display: inline-block !important;';
    
    var finLabel = document.createElement('label');
    finLabel.innerHTML = "Année de fin :";
    finLabel.style = '';
    var fin = document.createElement('select');
    fin.id = "finAnnee";
    fin.className = "selectpicker selectTD";
    fin.style = 'display: inline-block !important;';
    
    var firstOption1 = document.createElement('option');
    firstOption1.value = '';
    firstOption1.innerHTML = 'Choisir une date...';
    var firstOption2 = document.createElement('option');
    firstOption2.value = '';
    firstOption2.innerHTML = 'Choisir une date...';
    debut.appendChild(firstOption1);
    fin.appendChild(firstOption2);
    
    for (var i = minDate; i<=maxDate; i++){
        var opt1 = document.createElement('option');
        var opt2 = document.createElement('option');
        opt1.value = i;
        opt1.innerHTML = i;
        opt2.value = i;
        opt2.innerHTML = i;
        debut.appendChild(opt1);
        fin.appendChild(opt2);
    }
    
    newIntervalDiv.appendChild(debutLabel);
    newIntervalDiv.appendChild(debut);
    newIntervalDiv.appendChild(finLabel);
    newIntervalDiv.appendChild(fin);
    
    dateDiv.appendChild(newIntervalDiv);
    
    //$(debut).selectpicker();
    //$(fin).selectpicker();
    
}

function fillDataTable(jsonData){
    
    $('#listeFilms').DataTable().clear().draw();
    
    for(var i = 0; i<jsonData.length; i++){
        
        var film = jsonData[i];
        var id = film[0];
        var title = film[1];
        var year = film[2];
        
        var newRow = document.createElement("tr");
        newRow.setAttribute("data-id",id);
        newRow.className = "dataTableRow";
        newRow.setAttribute("onclick","getFilmInfo('"+id+"');");
        
        var titleTD = document.createElement("td");
        titleTD.innerHTML = title;
        titleTD.className = "dataTableTitleTD";
        var yearTD = document.createElement("td");
        yearTD.innerHTML = year;
        yearTD.className = "dataTableYearTD";
        
        newRow.appendChild(titleTD);
        newRow.appendChild(yearTD);
        
        //filmTableBody.appendChild(newRow);
        $('#listeFilms').DataTable().row.add(newRow).draw();
    }
    
    hideSpinner();
    
}

function sendToWebService(rechercheFilms){
    
    //alert(JSON.stringify(rechercheFilms));
    
    showSpinner();
    
    if(JSON.stringify(rechercheFilms) !== JSON.stringify({})){
        var filmSearchData = JSON.stringify(rechercheFilms);
        $.ajax({
            type: "POST",
            url: "./webresources/film/rechercheAvancee",
            headers: { 
                'Accept': 'application/json',
                'Content-Type': 'application/json' 
            },
            contentType: "application/json",
            dataType: "json",
            data: filmSearchData,
            success: function (data) {
                console.log(data);
                //fillDataTable(data);
                $(location).attr('href', './recherche');
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
    else{hideSpinner();}
}

function showSpinner(){
    $('#spinner').show();
}

function hideSpinner(){
    $('#spinner').hide();
}


function getAllFilmGenres(){
    $.ajax({
        type: "GET",
        url: "./webresources/film/getAllFilmGenres",
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        contentType: "application/json",
        dataType: "json",
        data: "GENRES",
        success: function (data) {
            var genresSelect = document.getElementById("genres");
            for(var i = 0; i<data.length; i++){
                var genre = data[i];
                var newOption = document.createElement("option");
                newOption.value = genre;
                newOption.innerHTML = genre;
                genresSelect.appendChild(newOption);
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            console.log(status);
            console.log(error);
        }
    });
}

function getAllFilmCountries(){
    $.ajax({
        type: "GET",
        url: "./webresources/film/getAllFilmCountries",
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        contentType: "application/json",
        dataType: "json",
        data: "COUNTRIES",
        success: function (data) {
            var paysSelect = document.getElementById("pays");
            for(var i = 0; i<data.length; i++){
                var pays = data[i];
                var newOption = document.createElement("option");
                newOption.value = pays;
                newOption.innerHTML = pays;
                paysSelect.appendChild(newOption);
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            console.log(status);
            console.log(error);
        }
    });
}

function getAllFilmLanguages(){
    $.ajax({
        type: "GET",
        url: "./webresources/film/getAllFilmLanguages",
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        contentType: "application/json",
        dataType: "json",
        data: "LANGUAGES",
        success: function (data) {
            var languesSelect = document.getElementById("languesOriginales");
            for(var i = 0; i<data.length; i++){
                var langue = data[i];
                var newOption = document.createElement("option");
                newOption.value = langue;
                newOption.innerHTML = langue;
                languesSelect.appendChild(newOption);
            }
        },
        error: function (xhr, status, error) {
            console.log(xhr.responseText);
            console.log(status);
            console.log(error);
        }
    });
}


function getFilmInfo(id){
    
    showSpinner();
    if(id !== undefined && id !== null && id !== ""){     
        $.ajax({
            type: "GET",
            url: "./webresources/film/afficher/"+id,
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

