import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'SEPM';
  
  afuConfig = {
    formatsAllowed: ".csv,.xlsx",

    uploadAPI: {
      url:"https://slack.com/api/files.upload"
    }
};
}
