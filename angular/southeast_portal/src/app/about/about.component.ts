import { Component } from '@angular/core';
import { AboutService } from '../services/openapi/services/services/about.service';
import { About } from '../services/openapi/services/models';

@Component({
  selector: 'app-about',
  templateUrl: './about.component.html',
  styleUrls: ['./about.component.css']
})
export class AboutComponent {

  aboutPage: About = {};
  achivementsLength: number = 0;

  constructor(private service: AboutService){}

  ngOnInit(){
    this.service.getById('1').subscribe(data =>{
      this.aboutPage = data;
      console.log(this.aboutPage);
      if(this.aboutPage.achievements?.length != undefined){
        this.achivementsLength = this.aboutPage.achievements.length;
      }
      
    })
  }
}
