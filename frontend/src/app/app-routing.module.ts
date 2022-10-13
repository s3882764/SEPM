import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainRoutingModule } from './main/main-routing.module';
import { TeamsComponent } from './main/teams/teams.component';
import { UploadComponent } from './main/upload/upload.component';

const routes: Routes = [
  { path: '', component: UploadComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes), MainRoutingModule],
  exports: [RouterModule]
})
export class AppRoutingModule { }
