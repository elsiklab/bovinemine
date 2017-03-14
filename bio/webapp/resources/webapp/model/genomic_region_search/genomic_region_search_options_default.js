//=== A mine specific script ===
//=== This is the default script for generic purpose ===

    jQuery(document).ready(function() {

        var htmlToInsert = '<li>' +
                           '<span>Select Organism:&nbsp;</span>' +
                           '<select id="organisms" name="organism">';

        // iterate through the object
        jQuery.each(webDataJSON.organisms, function() {
            htmlToInsert += '<option value="'+this+'">'+this+'</option>';
        });

        htmlToInsert += '</select>' + '<span id="genomeBuild" style="padding:10px;"></span>'
                        '</li><br>';

        htmlToInsert += '<li>' +
                        '<p id="selectFeatureTypes" style="padding-bottom:8px;"></p>' +
                        '<table id="featureTypes" cellpadding="0" cellspacing="0" border="0">' +
                        '</table>' +
                        '</li>' +
                        '<br>';

        jQuery(htmlToInsert).insertBefore('#genomicRegionInput');

        // when organism changes, the feature types will change accordingly
        jQuery("#organisms").change(function () {

            // Reset textarea and file input
            resetInputs();

            jQuery("#organisms option:selected").each(function () {
//                appendGenomeBuild(jQuery(this).text());
                appendFeatureTypes(jQuery(this).text());
            });
        })
        .trigger('change');
    });

   function appendGenomeBuild(org) {
       for(i in webDataJSON.genomeBuilds){
           if (webDataJSON.genomeBuilds[i].organism == org) {
               jQuery("#genomeBuild").html("<i>genome build: <span id='current-genome-version'>" + webDataJSON.genomeBuilds[i].genomeBuild + "</span></i>");
           }
       }

   }

   function appendFeatureTypes(org) {

        var featureTypes = jQuery("#featureTypes").empty(),
            row = "<tr></tr>",
            input = "<input type='checkbox' checked='yes' class='featureType' name='featureTypes'>",
            cell = "<td width='300'></td>",
            br = "<br/>",
            sp = "&nbsp;",
            onClick = function() {uncheck(this.checked, 'featureTypes')},
            columns = 3;

         for(var i in webDataJSON.featureTypes){
               if (webDataJSON.featureTypes[i].organism == org) {
                     var feature_size = webDataJSON.featureTypes[i].features.length,
                         rows = Math.ceil(feature_size/columns);

                     for (j = 0; j < rows; j++)
                     {
                        var rowElem = jQuery(row);
                        for (k = 0; k < columns; k++)
                        {
                            var current_loc = j + k*rows;
                            if (!(current_loc >= feature_size)) {
                                var current = webDataJSON.featureTypes[i].features[current_loc].featureType;
                                if (! $MODEL_TRANSLATION_TABLE[current]) {
                                    continue;
                                }
                                var displayName = $MODEL_TRANSLATION_TABLE[current].displayName ? $MODEL_TRANSLATION_TABLE[current].displayName : current;
                                var description = webDataJSON.featureTypes[i].features[current_loc].description;
                                var desBox = "<a onclick=\"document.getElementById('ctxHelpTxt').innerHTML='" + displayName + ": " + description.replace(/&apos;/g, "\\'")
                                             + "';document.getElementById('ctxHelpDiv').style.display=''; window.scrollTo(0, 0);return false\" title=\"" + description
                                             + "\"><img class=\"tinyQuestionMark\" src=\"images/icons/information-small-blue.png\" alt=\"?\" style=\"padding: 4px 3px\"></a>"
                                var cellElem = jQuery(cell);
                                var ckbx = jQuery(input).attr("value", current).click(onClick);
                                cellElem.append(ckbx).append(sp).append(displayName).append(desBox);
                                rowElem.append(cellElem);
                            }
                        }
                        featureTypes.append(rowElem);
                    }
               }
         }

         if (featureTypes.children.length) {
             jQuery("#selectFeatureTypes").html("<input id=\"check\" type=\"checkbox\" checked=\"yes\" onclick=\"checkAll(this.id)\"/>&nbsp;Select Feature Types:");
         }
         else {
             jQuery("#selectFeatureTypes").html("Select Feature Types:<br><i>"+org+" does not have any features</i>");
         }
   }

   // (un)Check all featureType checkboxes
   function checkAll(id)
   {
     jQuery(".featureType").prop('checked', jQuery('#' + id).is(':checked'));
     jQuery("#check").css("opacity", 1);
   }

   // check/uncheck any featureType checkbox
   function uncheck(status, name)
   {
         var statTag;
         if (!status) { //unchecked
           jQuery(".featureType").each(function() {
             if (this.checked) {statTag=true;}
           });

           if (statTag) {
            jQuery("#check").prop('checked', true);
            jQuery("#check").css("opacity", 0.5); }
           else {
            jQuery("#check").removeAttr('checked');
            jQuery("#check").css("opacity", 1);}
         }
         else { //checked
           jQuery(".featureType").each(function() {
             if (!this.checked) {statTag=true;}
         });

         if (statTag) {
           jQuery("#check").prop('checked', true);
           jQuery("#check").css("opacity", 0.5); }
         else {
           jQuery("#check").prop('checked', true);
           jQuery("#check").css("opacity", 1);}
         }
   }

   function validateBeforeSubmit() {
       var checkedFeatureTypes = [];
       jQuery(".featureType").each(function() {
           if (this.checked) { checkedFeatureTypes.push(this.value); }
       });
       var checkedFeatureTypesToString = checkedFeatureTypes.join(",");

       if (jQuery(".featureType").val() == null || checkedFeatureTypesToString == "") {
           alert("Please select some feature types...");
           return false;
       }

       if (jQuery("#pasteInput").val() == "" && jQuery("#fileInput").val() == "") {
           alert("Please type/paste/upload some genome regions...");
           return false;
       }

       if (jQuery("#pasteInput").val() != "") {
             // Regex validation
             var ddotsRegex = /^[^:\t\s]+: ?\d+(,\d+)*\.\.\d+(,\d+)*$/;
             var tabRegex = /^[^\t\s]+(\t\d+(,\d+)*){2}/; // this will match the line start with
             var dashRegex = /^[^:\t\s]+: ?\d+(,\d+)*\-\d+(,\d+)*$/;
             var snpRegex = /^[^:\t\s]+: ?\d+(,\d+)*$/;
             var emptyLine = /^\s*$/;
             var ddotstagRegex = /^[^:]+: ?\d+(,\d+)*\.\.\d+(,\d+)*: ?\d+$/;

             var spanArray = jQuery.trim(jQuery("#pasteInput").val()).split("\n");
             var lineNum;
             for (i=0;i<spanArray.length;i++) {
               lineNum = i + 1;
               if (spanArray[i] == "") {
                   alert("Line " + lineNum + " is empty...");
                   return false;
               }
               if (!spanArray[i].match(ddotsRegex) &&
                   !spanArray[i].match(ddotstagRegex) &&
                   !spanArray[i].match(tabRegex) &&
                   !spanArray[i].match(dashRegex) &&
                   !spanArray[i].match(snpRegex) &&
                   !spanArray[i].match(emptyLine)
                   ) {
                      alert(spanArray[i] + " doesn't match any supported format...");
                      return false;
               }
               if (spanArray[i].match(ddotsRegex)) {
                   var start = parseInt(spanArray[i].split(":")[1].split("..")[0].replace(/\,/g,''));
                   var end = parseInt(spanArray[i].split(":")[1].split("..")[1].replace(/\,/g,''));
               }
               if (spanArray[i].match(tabRegex)) {
                   var start = parseInt(spanArray[i].split("\t")[1].replace(/\,/g,''));
                   var end = parseInt(spanArray[i].split("\t")[2].replace(/\,/g,''));
               }
               if (spanArray[i].match(dashRegex)) {
                   var start = parseInt(spanArray[i].split(":")[1].split("-")[0].replace(/\,/g,''));
                   var end = parseInt(spanArray[i].split(":")[1].split("-")[1].replace(/\,/g,''));
               }
         }
       }
       return true;
   }

   function loadExample(exampleSpans) {
 switchInputs('paste','file');
      jQuery('#pasteInput').focus();
        jQuery('#pasteInput').val("GK000001.2:7901376-7901377\n"+
"GK000003.2:80105316-80105317\n"+
"GK000003.2:88904960-88904961\n"+
"GK000004.2:7139260-7139261\n"+
"GK000004.2:75484332-75484333\n"+
"GK000005.2:47594268-47594269\n"+
"GK000005.2:47866991-47866992\n"+
"GK000005.2:48623407-48623408\n"+
"GK000005.2:48876680-48876681\n"+
"GK000005.2:49341986-49341987\n"+
"GK000005.2:50511526-50511527\n"+
"GK000006.2:40093712-40093713\n"+
"GK000006.2:40093712-40093713\n"+
"GK000006.2:40093712-40093713\n"+
"GK000006.2:68101121-68101122\n"+
"GK000006.2:103056415-103056416\n"+
"GK000007.2:93287387-93287388\n"+
"GK000007.2:98540675-98540676\n"+
"GK000008.2:88601164-88601165\n"+
"GK000009.2:81368713-81368714\n"+
"GK000009.2:99124601-99124602\n"+
"GK000010.2:94456158-94456159\n"+
"GK000010.2:96286865-96286866\n"+
"GK000011.2:103650142-103650143\n"+
"GK000011.2:104721167-104721168\n"+
"GK000012.2:28414761-28414762\n"+
"GK000012.2:35342256-35342257\n"+
"GK000014.2:24573257-24573258\n"+
"GK000014.2:24621142-24621143\n"+
"GK000014.2:24973324-24973325\n"+
"GK000014.2:25015640-25015641\n"+
"GK000014.2:25015640-25015641\n"+
"GK000014.2:25015640-25015641\n"+
"GK000014.2:25284162-25284163\n"+
"GK000014.2:26244461-26244462\n"+
"GK000014.2:49295027-49295028\n"+
"GK000014.2:57668819-57668820\n"+
"GK000016.2:11142022-11142023\n"+
"GK000016.2:73527778-73527779\n"+
"GK000017.2:25138316-25138317\n"+
"GK000017.2:49580330-49580331\n"+
"GK000020.2:4873556-4873557\n"+
"GK000020.2:16773483-16773484\n"+
"GK000020.2:28193857-28193858\n"+
"GK000021.2:21396681-21396682\n"+
"GK000021.2:21751432-21751433\n"+
"GK000021.2:27166480-27166481\n"+
"GK000021.2:32790802-32790803\n"+
"GK000025.2:10486776-10486777\n"+
"GK000025.2:25631487-25631488\n"+
"GK000029.2:36095196-36095197\n"+
"GK000029.2:45556241-45556242");
    }




   function loadExample2() {
      switchInputs('paste','file');
      jQuery('#pasteInput').focus();
        jQuery('#pasteInput').val("GK000001.2:7901376..7901377\n"+
"GK000003.2:80105316..80105317\n"+
"GK000003.2:88904960..88904961\n"+
"GK000004.2:7139260..7139261\n"+
"GK000004.2:75484332..75484333\n"+
"GK000005.2:47594268..47594269\n"+
"GK000005.2:47866991..47866992\n"+
"GK000005.2:48623407..48623408\n"+
"GK000005.2:48876680..48876681\n"+
"GK000005.2:49341986..49341987\n"+
"GK000005.2:50511526..50511527\n"+
"GK000006.2:40093712..40093713\n"+
"GK000006.2:40093712..40093713\n"+
"GK000006.2:40093712..40093713\n"+
"GK000006.2:68101121..68101122\n"+
"GK000006.2:103056415..103056416\n"+
"GK000007.2:93287387..93287388\n"+
"GK000007.2:98540675..98540676\n"+
"GK000008.2:88601164..88601165\n"+
"GK000009.2:81368713..81368714\n"+
"GK000009.2:99124601..99124602\n"+
"GK000010.2:94456158..94456159\n"+
"GK000010.2:96286865..96286866\n"+
"GK000011.2:103650142..103650143\n"+
"GK000011.2:104721167..104721168\n"+
"GK000012.2:28414761..28414762\n"+
"GK000012.2:35342256..35342257\n"+
"GK000014.2:24573257..24573258\n"+
"GK000014.2:24621142..24621143\n"+
"GK000014.2:24973324..24973325\n"+
"GK000014.2:25015640..25015641\n"+
"GK000014.2:25015640..25015641\n"+
"GK000014.2:25015640..25015641\n"+
"GK000014.2:25284162..25284163\n"+
"GK000014.2:26244461..26244462\n"+
"GK000014.2:49295027..49295028\n"+
"GK000014.2:57668819..57668820\n"+
"GK000016.2:11142022..11142023\n"+
"GK000016.2:73527778..73527779\n"+
"GK000017.2:25138316..25138317\n"+
"GK000017.2:49580330..49580331\n"+
"GK000020.2:4873556..4873557\n"+
"GK000020.2:16773483..16773484\n"+
"GK000020.2:28193857..28193858\n"+
"GK000021.2:21396681..21396682\n"+
"GK000021.2:21751432..21751433\n"+
"GK000021.2:27166480..27166481\n"+
"GK000021.2:32790802..32790803\n"+
"GK000025.2:10486776..10486777\n"+
"GK000025.2:25631487..25631488\n"+
"GK000029.2:36095196..36095197\n"+
"GK000029.2:45556241..45556242");
    }

   function loadExample3() {
      switchInputs('paste','file');
      jQuery('#pasteInput').focus();
        jQuery('#pasteInput').val("GK000001.2	7901376	7901377\n"+
"GK000003.2	80105316	80105317\n"+
"GK000003.2	88904960	88904961\n"+
"GK000004.2	7139260	7139261\n"+
"GK000004.2	75484332	75484333\n"+
"GK000005.2	47594268	47594269\n"+
"GK000005.2	47866991	47866992\n"+
"GK000005.2	48623407	48623408\n"+
"GK000005.2	48876680	48876681\n"+
"GK000005.2	49341986	49341987\n"+
"GK000005.2	50511526	50511527\n"+
"GK000006.2	40093712	40093713\n"+
"GK000006.2	40093712	40093713\n"+
"GK000006.2	40093712	40093713\n"+
"GK000006.2	68101121	68101122\n"+
"GK000006.2	103056415	103056416\n"+
"GK000007.2	93287387	93287388\n"+
"GK000007.2	98540675	98540676\n"+
"GK000008.2	88601164	88601165\n"+
"GK000009.2	81368713	81368714\n"+
"GK000009.2	99124601	99124602\n"+
"GK000010.2	94456158	94456159\n"+
"GK000010.2	96286865	96286866\n"+
"GK000011.2	103650142	103650143\n"+
"GK000011.2	104721167	104721168\n"+
"GK000012.2	28414761	28414762\n"+
"GK000012.2	35342256	35342257\n"+
"GK000014.2	24573257	24573258\n"+
"GK000014.2	24621142	24621143\n"+
"GK000014.2	24973324	24973325\n"+
"GK000014.2	25015640	25015641\n"+
"GK000014.2	25015640	25015641\n"+
"GK000014.2	25015640	25015641\n"+
"GK000014.2	25284162	25284163\n"+
"GK000014.2	26244461	26244462\n"+
"GK000014.2	49295027	49295028\n"+
"GK000014.2	57668819	57668820\n"+
"GK000016.2	11142022	11142023\n"+
"GK000016.2	73527778	73527779\n"+
"GK000017.2	25138316	25138317\n"+
"GK000017.2	49580330	49580331\n"+
"GK000020.2	4873556	4873557\n"+
"GK000020.2	16773483	16773484\n"+
"GK000020.2	28193857	28193858\n"+
"GK000021.2	21396681	21396682\n"+
"GK000021.2	21751432	21751433\n"+
"GK000021.2	27166480	27166481\n"+
"GK000021.2	32790802	32790803\n"+
"GK000025.2	10486776	10486777\n"+
"GK000025.2	25631487	25631488\n"+
"GK000029.2	36095196	36095197\n"+
"GK000029.2	45556241	45556242");
    }
