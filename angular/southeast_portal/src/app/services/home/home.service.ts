import { HttpClient } from '@angular/common/http';
import { Inject, Injectable } from '@angular/core';
import { config } from 'rxjs';
import { AppConfig } from 'src/app/AppConfig/appconfig.interface';
import { APP_SERVICE, APP_SERVICE_CONFIG } from 'src/app/AppConfig/appconfig.service';
import { HomeModel } from 'src/app/model/home/home.model';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  constructor(
    @Inject(APP_SERVICE_CONFIG) private config: AppConfig, private http: HttpClient
  ) { }

  getHome(id:number){
    
    return this.http.get<HomeModel>(APP_SERVICE.apiEndpoint+"page/home/get/"+id);
  }

}
