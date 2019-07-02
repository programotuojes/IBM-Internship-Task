import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { BroadcastService } from './broadcast.service';
import { HttpClientModule } from '@angular/common/http';
import { DataComponent } from './data/data.component';

@NgModule({
  declarations: [
    AppComponent,
    DataComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule
  ],
  providers: [BroadcastService],
  bootstrap: [AppComponent]
})
export class AppModule { }
