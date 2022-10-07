const HOST = "http://localhost:8080";

/*-----------------------------CREATE------------------------------------------*/

function createEmployee(){

    const firstname = document.getElementById('firstname').value;
    const lastname = document.getElementById('lastname').value;
    const startDate = document.getElementById('startDate').value;
    const address = document.getElementById('address').value;
    const phone = document.getElementById('phone').value;
    const departmentId = parseInt(document.getElementById('departmentId').value);
    const positionId = parseInt(document.getElementById('positionId').value);
    const seniorityId = parseInt(document.getElementById('seniorityId').value);
    
    $.ajax({

        method: "post",
        url: `${HOST}/employees/`,
        data: JSON.stringify ({
            "firstname": firstname,
            "lastname": lastname,
            "startDate": startDate,
            "address": address,
            "phone": phone,
            "departmentId": departmentId,
            "positionId": positionId,
            "seniorityId": seniorityId
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('employeeCreated').innerHTML = "The employee, "+firstname+" "+lastname+", has been registered!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

function createDepartment(){

    const depName = document.getElementById('depName').value;
    const depAddress = document.getElementById('depAddress').value;
    const depPhone = document.getElementById('depPhone').value;
    
    $.ajax({

        method: "post",
        url: `${HOST}/departments/`,
        data: JSON.stringify ({
            "depName": depName,
            "depAddress": depAddress,
            "depPhone": depPhone
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('departmentCreated').innerHTML = "The department, "+depName+", has been registered!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

function createPosition(){

    const posName = document.getElementById('posName').value;
    
    $.ajax({

        url: `${HOST}/positions/`,
        method: "post",
        data: JSON.stringify ({
            "posName": posName
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById("positionCreated").innerHTML = "The position, "+posName+", has been registered!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

function createSeniority(){

    const senName = document.getElementById('senName').value;
    
    $.ajax({

        method: "post",
        url: `${HOST}/seniorities/`,
        data: JSON.stringify ({
            "senName": senName
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('seniorityCreated').innerHTML = "The seniority level, "+senName+", has been registered!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

/*----------------------Fetch--Employees----------------------------------------------------------*/

function getManagerByDepName(){

    const departName = document.getElementById('departName').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/joined/depName/${departName}`,
            
        }).done((response) => {

            let string = '<caption>Manager by department</caption>'+
                         '<tr><th>ID</th><th>First name</th><th>Last name</th><th>Department</th><th>Phone</th><th>Position</th><th>Seniority</th></tr>'+
                         '<tr>'+
                         '<td>'+response.id+'</td>'+
                         '<td>'+response.firstname+'</td>'+
                         '<td>'+response.lastname+'</td>'+
                         '<td>'+response.depName+'</td>'+
                         '<td>'+response.depPhone+'</td>'+
                         '<td>'+response.posName+'</td>'+
                         '<td>'+response.senName+'</td>'+
                         '</tr>'
             
            document.getElementById("employeesFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function getEmployeeById(){

    let employeeId = document.getElementById('employeeId').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/employees/id/${employeeId}`,
            
        }).done((response) => {

            let string = '<caption>Employee by id</caption>'+
                         '<tr><th>ID</th><th>First name</th><th>Last name</th><th>Start</th><th>End</th><th>Address</th><th>Phone</th><th>Dept. Id</th><th>Position Id</th><th>Seniority Id</th></tr>'+
                         '<tr>'+
                         '<td>'+response.id+'</td>'+
                         '<td>'+response.firstname+'</td>'+
                         '<td>'+response.lastname+'</td>'+
                         '<td>'+response.startDate+'</td>'+
                         '<td>'+response.endDate+'</td>'+
                         '<td>'+response.address+'</td>'+
                         '<td>'+response.phone+'</td>'+
                         '<td>'+response.departmentId+'</td>'+
                         '<td>'+response.positionId+'</td>'+
                         '<td>'+response.seniorityId+'</td>'+
                         '</tr>'
             
            document.getElementById("employeesFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function getEmployeeByName(){

    let lName = document.getElementById('lName').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/employees/lastname/${lName}`,
            
        }).done((response) => {

            let string = '<caption>Employee by last name</caption>'+
                         '<tr><th>ID</th><th>First name</th><th>Last name</th><th>Start</th><th>End</th><th>Address</th><th>Phone</th><th>Dept. Id</th><th>Position Id</th><th>Seniority Id</th></tr>'+
                         '<tr>'+
                         '<td>'+response.id+'</td>'+
                         '<td>'+response.firstname+'</td>'+
                         '<td>'+response.lastname+'</td>'+
                         '<td>'+response.startDate+'</td>'+
                         '<td>'+response.endDate+'</td>'+
                         '<td>'+response.address+'</td>'+
                         '<td>'+response.phone+'</td>'+
                         '<td>'+response.departmentId+'</td>'+
                         '<td>'+response.positionId+'</td>'+
                         '<td>'+response.seniorityId+'</td>'+
                         '</tr>'
             
            document.getElementById("employeesFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function getAllEmployees(){

    $.ajax(
        {
            method: "get",
            url: `${HOST}/employees/`,
            
        }).done((response) => {

            let string = '<caption>The list of Employees</caption>'+
                         '<tr><th>ID</th><th>First name</th><th>Last name</th><th>Start</th><th>End</th><th>Address</th><th>Phone</th><th>Dept. Id</th><th>Position Id</th><th>Seniority Id</th></tr>';
                         
                         for(let i=0; i<response.length; i++){
                             string += '<tr>'+
                             '<td>'+response[i].id+'</td>'+
                             '<td>'+response[i].firstname+'</td>'+
                             '<td>'+response[i].lastname+'</td>'+
                             '<td>'+response[i].startDate+'</td>'+
                             '<td>'+response[i].endDate+'</td>'+
                             '<td>'+response[i].address+'</td>'+
                             '<td>'+response[i].phone+'</td>'+
                             '<td>'+response[i].departmentId+'</td>'+
                             '<td>'+response[i].positionId+'</td>'+
                             '<td>'+response[i].seniorityId+'</td>'+
                             '</tr>'
                         }           
             
            document.getElementById("employeesFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function getManagers(){

    $.ajax(
        {
            method: "get",
            url: `${HOST}/joined/managers/`,
            
        }).done((response) => {

            let string = '<caption>The list of Managers</caption>'+
            '<tr><th>ID</th><th>First name</th><th>Last name</th><th>Department</th><th>Phone</th><th>Position</th><th>Seniority</th></tr>';
                         
                         for(let i=0; i<response.length; i++){
                             string += '<tr>'+
                             '<td>'+response[i].id+'</td>'+
                         '<td>'+response[i].firstname+'</td>'+
                         '<td>'+response[i].lastname+'</td>'+
                         '<td>'+response[i].depName+'</td>'+
                         '<td>'+response[i].depPhone+'</td>'+
                         '<td>'+response[i].posName+'</td>'+
                         '<td>'+response[i].senName+'</td>'+
                         '</tr>'
                         }           
             
            document.getElementById("employeesFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

/*----------------------Fetch--Departments----------------------------------------------------------*/

function getAllDepartments(){

    $.ajax(
        {
            method: "get",
            url: `${HOST}/departments/`,
            
        }).done((response) => {

            let string = '<caption>The list of  Departments</caption>'+
                         '<tr><th>Department id</th><th>Department name</th><th>Address</th><th>Phone</th></tr>';
                         
                         for(let i=0; i<response.length; i++){
                             string += '<tr>'+
                             '<td>'+response[i].depId+'</td>'+
                             '<td>'+response[i].depName+'</td>'+
                             '<td>'+response[i].depAddress+'</td>'+
                             '<td>'+response[i].depPhone+'</td>'+
                             '</tr>'
                         }           
             
            document.getElementById("departmentsFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function getDepartmentById(){

    let dId = document.getElementById('dId').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/departments/depId/${dId}`,
            
        }).done((response) => {

            let string = '<caption>Department by id</caption>'+
                         '<tr><th>Department id</th><th>Department name</th><th>Address</th><th>Phone</th></tr>'+
                         '<tr>'+
                         '<td>'+response.depId+'</td>'+
                         '<td>'+response.depName+'</td>'+
                         '<td>'+response.depAddress+'</td>'+
                         '<td>'+response.depPhone+'</td>'+
                         '</tr>'
             
            document.getElementById("departmentsFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function getDepartmentByName(){

    let dName = document.getElementById('dName').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/departments/depName/${dName}`,
            
        }).done((response) => {

            let string = '<caption>Department by name</caption>'+
                         '<tr><th>Department id</th><th>Department name</th><th>Address</th><th>Phone</th></tr>'+
                         '<tr>'+
                         '<td>'+response.depId+'</td>'+
                         '<td>'+response.depName+'</td>'+
                         '<td>'+response.depAddress+'</td>'+
                         '<td>'+response.depPhone+'</td>'+
                         '</tr>'
             
            document.getElementById("departmentsFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

/*----------------------Fetch--Positions----------------------------------------------------------*/

function getAllPositions(){

    $.ajax(
        {
            method: "get",
            url: `${HOST}/positions/`,
            
        }).done((response) => {

            let string = '<caption>The list of  Positions</caption>'+
                         '<tr><th>Position id</th><th>Position name</th></tr>';
                         
                         for(let i=0; i<response.length; i++){
                             string += '<tr>'+
                             '<td>'+response[i].posId+'</td>'+
                             '<td>'+response[i].posName+'</td>'+
                             '</tr>'
                         }           
             
            document.getElementById("positionsFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function getPositionById(){

    let pId = document.getElementById('pId').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/positions/posId/${pId}`,
            
        }).done((response) => {

            let string = '<caption>Position by id</caption>'+
                         '<tr><th>Position id</th><th>Position name</th></tr>'+
                         '<tr>'+
                         '<td>'+response.posId+'</td>'+
                         '<td>'+response.posName+'</td>'+
                         '</tr>'
             
            document.getElementById("positionsFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

/*----------------------Fetch--Seniority----------------------------------------------------------*/

function getAllSeniorities(){

    $.ajax(
        {
            method: "get",
            url: `${HOST}/seniorities/`,
            
        }).done((response) => {

            let string = '<caption>The list of  Seniority Level</caption>'+
                         '<tr><th>Seniority id</th><th>Seniority Level</th></tr>';
                         
                         for(let i=0; i<response.length; i++){
                             string += '<tr>'+
                             '<td>'+response[i].senId+'</td>'+
                             '<td>'+response[i].senName+'</td>'+
                             '</tr>'
                         }           
             
            document.getElementById("senioritiesFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function getSeniorityById(){

    let sId = document.getElementById('sId').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/seniorities/senId/${sId}`,
            
        }).done((response) => {

            let string = '<caption>Seniority by id</caption>'+
                         '<tr><th>Seniority id</th><th>Seniority Level</th></tr>'+
                         '<tr>'+
                         '<td>'+response.senId+'</td>'+
                         '<td>'+response.senName+'</td>'+
                         '</tr>'
             
            document.getElementById("senioritiesFetched").innerHTML = string;
            

        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

/*----------------------Update------------------------------------------------------------*/

function getEmployeeByIdU(){

    let idU = document.getElementById('idU').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/employees/id/${idU}`,
            
        }).done((response) => {

            document.getElementById("firstnameU").value = response.firstname;
            document.getElementById("lastnameU").value = response.lastname;
            document.getElementById("startDateU").value = response.startDate;
            document.getElementById("endDateU").value = response.endDate;
            document.getElementById("addressU").value = response.address;
            document.getElementById("phoneU").value = response.phone;
            document.getElementById("departmentIdU").value = response.departmentId;
            document.getElementById("positionIdU").value = response.positionId;
            document.getElementById("seniorityIdU").value = response.seniorityId;
            
        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function updatedEmployee(){
    let idU = document.getElementById('idU').value;
    let firstname = document.getElementById('firstnameU').value;
    let lastname = document.getElementById('lastnameU').value;
    let startDate = document.getElementById('startDateU').value;
    let endDate = document.getElementById('endDateU').value;
    let address = document.getElementById('addressU').value;
    let phone = document.getElementById('phoneU').value;
    let departmentId = parseInt(document.getElementById('departmentIdU').value);
    let positionId = parseInt(document.getElementById('positionIdU').value);
    let seniorityId = parseInt(document.getElementById('seniorityIdU').value);
    
    $.ajax({

        method: "put",
        url: `${HOST}/employees/id/${idU}`,
        data: JSON.stringify ({
            "id": idU,
            "firstname": firstname,
            "lastname": lastname,
            "startDate": startDate,
            "endDate": endDate,
            "address": address,
            "phone": phone,
            "departmentId": departmentId,
            "positionId": positionId,
            "seniorityId": seniorityId
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('employeeUpdated').innerHTML = "The employee, "+firstname+" "+lastname+", has been updated!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

function getDepartmentByIdU(){

    let depIdU = document.getElementById('depIdU').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/departments/depId/${depIdU}`,
            
        }).done((response) => {

            document.getElementById("depNameU").value = response.depName;
            document.getElementById("depAddressU").value = response.depAddress;
            document.getElementById("depPhoneU").value = response.depPhone;
            
        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function updatedDepartment(){
    let depIdU = document.getElementById('depIdU').value;
    let depNameU = document.getElementById("depNameU").value;
    let depAddressU =document.getElementById("depAddressU").value;
    let depPhoneU = document.getElementById("depPhoneU").value;
    
    $.ajax({

        method: "put",
        url: `${HOST}/departments/depId/${depIdU}`,
        data: JSON.stringify ({
            "depId": depIdU,
            "depName": depNameU,
            "depAddress": depAddressU,
            "depPhone": depPhoneU
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('departmentUpdated').innerHTML = "The department, N."+depIdU+", has been updated!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

function getPositionByIdU(){

    let posIdU = document.getElementById('posIdU').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/positions/posId/${posIdU}`,
            
        }).done((response) => {

            document.getElementById("posNameU").value = response.posName;
            
        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function updatedPosition(){
    let posIdU = document.getElementById('posIdU').value;
    let posNameU = document.getElementById("posNameU").value;
    
    $.ajax({

        method: "put",
        url: `${HOST}/positions/posId/${posIdU}`,
        data: JSON.stringify ({
            "posId": posIdU,
            "posName": posNameU
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('positionUpdated').innerHTML = "The position, N."+posIdU+", has been updated!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

function getSeniorityByIdU(){

    let senIdU = document.getElementById('senIdU').value;

    $.ajax(
        {
            method: "get",
            url: `${HOST}/seniorities/senId/${senIdU}`,
            
        }).done((response) => {

            document.getElementById("senNameU").value = response.senName;
            
        }).fail((obj, textStatus)=>{
            if (obj && obj.responseJSON && obj.responseJSON.message){
                alert(obj.responseJSON.message)
            }
            if (obj && obj.responseText){
                alert(obj.responseText)
            }
        })
}

function updatedSeniority(){
    let senIdU = document.getElementById('senIdU').value;
    let senNameU = document.getElementById("senNameU").value;
    
    $.ajax({

        method: "put",
        url: `${HOST}/seniorities/senId/${senIdU}`,
        data: JSON.stringify ({
            "senId": senIdU,
            "senName": senNameU
        }),
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('seniorityUpdated').innerHTML = "The seniority level, N."+senIdU+", has been updated!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

/*----------------------Delete------------------------------------------------------------*/

function deletedEmployee(){
    let idD = document.getElementById('idD').value;
    
    $.ajax({

        method: "delete",
        url: `${HOST}/employees/id/${idD}`,
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('employeeDeleted').innerHTML = "The employee, N."+idD+", has been deleted!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

function deletedDepartment(){
    let depIdD = document.getElementById('depIdD').value;
    
    $.ajax({

        method: "delete",
        url: `${HOST}/departments/depId/${depIdD}`,
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('departmentDeleted').innerHTML = "The department, N."+depIdD+", has been deleted!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

function deletedPosition(){
    let posIdD = document.getElementById('posIdD').value;
    
    $.ajax({

        method: "delete",
        url: `${HOST}/positions/posId/${posIdD}`,
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('positionDeleted').innerHTML = "The position, N."+posIdD+", has been deleted!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

function deletedSeniority(){
    let senIdD = document.getElementById('senIdD').value;
    
    $.ajax({

        method: "delete",
        url: `${HOST}/seniorities/senId/${senIdD}`,
        
        headers: {
            'Accept': 'application/json',
            'Content-type': 'application/json'
        }
    }).done((response) => {
        document.getElementById('seniorityDeleted').innerHTML = "The seniority level, N."+senIdD+", has been deleted!";
    }   
    ).fail((obj, textStatus)=>{
        //An error is 400 or 500
        if (obj && obj.responseJSON && obj.responseJSON.message){
            alert(obj.responseJSON.message)
        }
        if (obj && obj.responseText){
            alert(obj.responseText)
        }
    })
}

/*----------------------display current section and hide others------------------------------------------------------------*/

function displayMenu(a, b, c, d, e){
    document.getElementById(a).style.display = "block";
    document.getElementById(b).style.display = "none";
    document.getElementById(c).style.display = "none";
    document.getElementById(d).style.display = "none";
    document.getElementById(e).style.display = "none";
}

function display(a, b, c, d){
    document.getElementById(a).style.display = "block";
    document.getElementById(b).style.display = "none";
    document.getElementById(c).style.display = "none";
    document.getElementById(d).style.display = "none";
}