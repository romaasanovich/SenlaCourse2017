import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {AppComponent} from './app.component';
import {AppRoutingModule} from './/app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {MasterComponent} from './master/master.component';
import {MasterDetailComponent} from './master-detail/master-detail.component';
import {MasterService} from './master.service';
import {SignInComponent} from './sign-in/sign-in.component';
import {SignInService} from './sign-in.service';
import {FormsModule} from '@angular/forms';
import {WorksComponent} from './works/works.component';
import {PlacesComponent} from './places/places.component';
import {WorkService} from './work.service';
import {PlaceService} from './place.service';
import {TableModule} from 'primeng/table';
import { OrdersComponent } from './orders/orders.component';



@NgModule({
  declarations: [
    AppComponent,
    MasterComponent,
    MasterDetailComponent,
    SignInComponent,
    WorksComponent,
    PlacesComponent,
    OrdersComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    FormsModule,
    TableModule
  ],
  providers: [MasterService, SignInService, WorkService, PlaceService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
