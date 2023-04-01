import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdPageComponent } from './ad-page/ad-page.component';
import { FavoritesPageComponent } from './favorites-page/favorites-page.component';
import { JobPageComponent } from './job-page/job-page.component';
import { MainPageComponent } from './main-page/main-page.component';
import { MessagesPageComponent } from './messages-page/messages-page.component';
import { RatingPageComponent } from './rating-page/rating-page.component';
import { UserPageComponent } from './user-page/user-page.component';

const routes: Routes = [
  {
    path: '', component: MainPageComponent,
    children: [
      { path:'user', component:UserPageComponent },
      { path:'messages', component:MessagesPageComponent },
      { path:'favorites', component:FavoritesPageComponent },
      { path:'jobs', component:JobPageComponent },
      { path:'ratings', component:RatingPageComponent },

      { path:'', component:AdPageComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MainRoutingModule { }
