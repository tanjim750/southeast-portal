import { Component } from '@angular/core';
import { HomeModel } from '../model/home/home.model';
import { Home } from '../services/openapi/services/models';
import { HomeService } from '../services/openapi/services/services';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent{
  homePage: HomeModel = {};
  

  constructor(
    private service: HomeService
  ){}

  ngOnInit(){
    this.service.getById('1').subscribe(data => {
      this.homePage = data;
    });


  }

}
