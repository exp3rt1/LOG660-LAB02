
var today = new Date();
var minDate = 1900;
var maxDate = today.getFullYear();  


$(document).ready(function(){
    
    setDatesInDateSelect();
    
    $('#listeFilms').DataTable({
                "bFilter": false,
                "bJQueryUI": true,
                "bProcessing": true,
                "bInfo": false,
                "bLengthChange": false,
                "bPaginate": true,
                "pageLength": 5,
                "pagingType": "simple_numbers",
                "dom": 'lrtip'
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
        rechercheFilms.anneesSortie = anneesSortie.split(',');
    }
    if(pays !== undefined && pays !== null && pays !== ""){
        rechercheFilms.pays = pays.split(',');
    }
    if(languesOriginales !== undefined && languesOriginales !== null && languesOriginales !== ""){
        rechercheFilms.languesOriginales = languesOriginales.split(',');
    }
    if(genres !== undefined && genres !== null && genres !== ""){
        rechercheFilms.genres = genres.split(',');
    }
    
    var intervallesAnnees = [];
    
    $('.dateInterval').each(function(loopIndex, intervalDiv) {
        var startDate = $(intervalDiv).find('#debutAnnee').val();
        var endDate = $(intervalDiv).find('#finAnnee').val();
        var startIsNull = (startDate === undefined || startDate === null || startDate === "");
        var endIsNull = (endDate === undefined || endDate === null || endDate === "");
        if(!startIsNull && endIsNull){
            endDate = '99999';
            intervallesAnnees.push("{'debut':'"+startDate+"','fin':'"+endDate+"'}");
        }
        else if(startIsNull && !endIsNull){
            startDate = '0';
            intervallesAnnees.push("{'debut':'"+startDate+"','fin':'"+endDate+"'}");
        }
        else if(!startIsNull && !endIsNull){
            intervallesAnnees.push("{'debut':'"+startDate+"','fin':'"+endDate+"'}");
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

function sendToWebService(rechercheFilms){
    alert(JSON.stringify(rechercheFilms));
    /*
    $.ajax({
        type: "POST",
        url: "/LOG660-LAB02/webresources/film/recherche",
        headers: { 
            'Accept': 'application/json',
            'Content-Type': 'application/json' 
        },
        contentType: "application/json",
        dataType: "application/json",
        data: rechercheFilms,
        success: function (data) {
            alert("tabarnak");
            console.log(data);
        },
        error: function (xhr) {
            alert(xhr.responseText);
        }
    });
    */
}

function setDatesInDateSelect() {
    var dates = document.getElementById('anneesSortie');
    var firstOption = document.createElement('option');
    firstOption.value = '';
    firstOption.innerHTML = 'Choisir une date...';
    dates.appendChild(firstOption);
    for (var i = minDate; i<=maxDate; i++){
        var opt = document.createElement('option');
        opt.value = i;
        opt.innerHTML = i;
        dates.appendChild(opt);
    }
    //$(dates).selectpicker('refresh');
}

function addDateInterval(){
    var dateDiv = document.getElementById("dateDiv");
   
    var newIntervalDiv = document.createElement("div");
    newIntervalDiv.className = "dateInterval";
    
    //'btn-group bootstrap-select show-tick'
      
    var debutLabel = document.createElement('label');
    debutLabel.innerHTML = "Année de début :";
    debutLabel.style = 'margin-left: 15px;';
    var debut = document.createElement('select');
    debut.id = "debutAnnee";
    debut.className = "selectpicker dateSelect";
    debut.style = 'display: block !important;';
    
    var finLabel = document.createElement('label');
    finLabel.innerHTML = "Année de fin :";
    finLabel.style = 'margin-left: 15px;';
    var fin = document.createElement('select');
    fin.id = "finAnnee";
    fin.className = "selectpicker dateSelect";
    fin.style = 'display: block !important;';
    
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
