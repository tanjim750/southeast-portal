import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { AboutComponent } from './about/about.component';
import { AcademicComponent } from './academic/academic.component';
import { AdministrationComponent } from './administration/administration.component';
import { AdmissionRequirementsComponent } from './admission-requirements/admission-requirements.component';
import { CampusLifeComponent } from './campus-life/campus-life.component';
import { ContactComponent } from './contact/contact.component';
import { HomeComponent } from './home/home.component';
import { PhotoGalleryComponent } from './photo-gallery/photo-gallery.component';
import { ProgramSingleComponent } from './program-single/program-single.component';
import { ResearchComponent } from './research/research.component';
import { ScholarshipComponent } from './scholarship/scholarship.component';
import { TuitionFeeComponent } from './tuition-fee/tuition-fee.component';
import { APP_SERVICE, APP_SERVICE_CONFIG } from './AppConfig/appconfig.service';

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    AboutComponent,
    AcademicComponent,
    AdministrationComponent,
    AdmissionRequirementsComponent,
    CampusLifeComponent,
    ContactComponent,
    HomeComponent,
    PhotoGalleryComponent,
    ProgramSingleComponent,
    ResearchComponent,
    ScholarshipComponent,
    TuitionFeeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: APP_SERVICE_CONFIG,
      useValue: APP_SERVICE
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
