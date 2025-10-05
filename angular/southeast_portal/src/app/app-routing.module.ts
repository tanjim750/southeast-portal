import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppComponent } from './app.component';
import { AboutComponent } from './about/about.component';
import { HomeComponent } from './home/home.component';
import { AcademicComponent } from './academic/academic.component';
import { AdministrationComponent } from './administration/administration.component';
import { AdmissionRequirementsComponent } from './admission-requirements/admission-requirements.component';
import { CampusLifeComponent } from './campus-life/campus-life.component';
import { ContactComponent } from './contact/contact.component';
import { PhotoGalleryComponent } from './photo-gallery/photo-gallery.component';
import { ProgramSingleComponent } from './program-single/program-single.component';
import { ResearchComponent } from './research/research.component';
import { ScholarshipComponent } from './scholarship/scholarship.component';
import { TuitionFeeComponent } from './tuition-fee/tuition-fee.component';

const routes: Routes = [
  { path: '', component: HomeComponent },  // Default route (Home)
  { path: 'about', component: AboutComponent },
  { path: 'academic', component: AcademicComponent },
  { path: 'administration', component: AdministrationComponent },
  { path: 'admission-requirements', component: AdmissionRequirementsComponent },
  { path: 'campus-life', component: CampusLifeComponent },
  { path: 'contact', component: ContactComponent },
  { path: 'photo-gallery', component: PhotoGalleryComponent },
  { path: 'program-single', component: ProgramSingleComponent },
  { path: 'research', component: ResearchComponent },
  { path: 'scholarship', component: ScholarshipComponent },
  { path: 'tuition-fee', component: TuitionFeeComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
