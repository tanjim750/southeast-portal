import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { AppConfig } from 'src/app/AppConfig/appconfig.interface';
import { APP_SERVICE } from 'src/app/AppConfig/appconfig.service';
import { AboutModel } from 'src/app/model/about/about.model';

@Injectable({
  providedIn: 'root'
})
export class AboutService {

  private config: AppConfig = APP_SERVICE;

  constructor(private http: HttpClient) { }

  getById(id: number): Observable<AboutModel>{
    return this.http.get<AboutModel>(this.config.apiEndpoint+"page/about/get/"+id);
  }
}
