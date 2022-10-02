import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MainRoutingModule } from './main-routing.module';
import { TeamsComponent } from './teams/teams.component';
import { UploadComponent } from './upload/upload.component';
import { AngularFileUploaderModule } from 'angular-file-uploader';


@NgModule({
  declarations: [
    TeamsComponent,
    UploadComponent
  ],
  imports: [
    CommonModule,
    MainRoutingModule,
    AngularFileUploaderModule
  ],
  providers: []
})
export class MainModule { }
