import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { RouterModule, Routes } from '@angular/router';
import { HttpClientModule } from "@angular/common/http";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { AuctionListComponent } from './auction-list/auction-list.component';
import { ModalModule, BsModalService } from 'ngx-bootstrap/modal';
import { FormsModule } from '@angular/forms';

const routers: Routes = [
  {path: 'home', component: HeaderComponent},
  {path: '', redirectTo: '/home',  pathMatch: 'full'}
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,    
    AuctionListComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    RouterModule.forRoot(routers),
    ModalModule.forRoot(),
    FormsModule
  ],
  providers: [BsModalService,AuctionListComponent],
  bootstrap: [AppComponent]
})
export class AppModule { }
