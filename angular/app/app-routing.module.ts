import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MasterComponent} from './master/master.component';
import {SignInComponent} from './sign-in/sign-in.component';
import {WorksComponent} from './works/works.component';
import {PlacesComponent} from './places/places.component';

const routes: Routes = [
  { path: 'master', component: MasterComponent },
  { path: 'signin', component: SignInComponent },
  { path: 'work', component: WorksComponent },
  { path: 'place', component: PlacesComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}

