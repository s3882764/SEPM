import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AngularFileUploaderConfig } from 'angular-file-uploader';

@Component({
  selector: 'app-upload',
  templateUrl: './upload.component.html',
  styleUrls: ['./upload.component.css']
})
export class UploadComponent implements OnInit {

  constructor(private router: Router) {}
  
  ngOnInit(): void {
    
  }

  title = 'SEPMProject';
  afuConfig : AngularFileUploaderConfig = {
    formatsAllowed: ".csv,.xlsx",
    uploadAPI: {
      url: "http://localhost:8080/reader/upload",
      method:"POST"
    },
    fileNameIndex: false
  };

  docUpload(event: any){
    console.log("Comes in here : " + event);
    console.dir(event);
  }

  fileSelected(event: any){
    console.log("Comes in file Selected : " + event)
    console.dir(event);
  }

  showTeams(){
    console.log("Is it here?");
    this.router.navigate(['/teams']); 
  }
}
