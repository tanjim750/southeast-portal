import { HttpClient, HttpContext, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { StrictHttpResponse } from '../strict-http-response';
import { RequestBuilder } from '../request-builder';

export class Operation{
    private urlPaths: UrlPath;

    constructor(urlPaths: UrlPath){
        this.urlPaths = urlPaths;
    }

    getAll(http: HttpClient, rootUrl: string, context?: HttpContext): Observable<StrictHttpResponse<{
    }>> {
      const rb = new RequestBuilder(rootUrl, this.urlPaths.get, 'get');
    
      return http.request(
        rb.build({ responseType: 'json', accept: '*/*', context })
      ).pipe(
        filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
        map((r: HttpResponse<any>) => {
          return r as StrictHttpResponse<{
          }>;
        })
      );
    }

    getById(http: HttpClient, rootUrl: string, id: string, context?: HttpContext): Observable<StrictHttpResponse<{
    }>> {
      const rb = new RequestBuilder(rootUrl, `${this.urlPaths.get}\/${id}`, 'get');
    
      return http.request(
        rb.build({ responseType: 'json', accept: '*/*', context })
      ).pipe(
        filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
        map((r: HttpResponse<any>) => {
          return r as StrictHttpResponse<{
          }>;
        })
      );
    }

    create(http: HttpClient, rootUrl: string, data: any, context?: HttpContext): Observable<StrictHttpResponse<{
    }>> {
      const rb = new RequestBuilder(rootUrl, this.urlPaths.create, 'post');
      if (data) {
        rb.body(data, 'application/json');
      }

      // Add Authorization header
      rb.header('Authorization', `Bearer ${this.getJwtToken()}`);
    
      return http.request(
        rb.build({ responseType: 'json', accept: '*/*', context })
      ).pipe(
        filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
        map((r: HttpResponse<any>) => {
          return r as StrictHttpResponse<{
          }>;
        })
      );
    }

    update(http: HttpClient, rootUrl: string, data: any, id:number, context?: HttpContext): Observable<StrictHttpResponse<{
    }>> {
      const rb = new RequestBuilder(rootUrl, `${this.urlPaths.update}\/${id}`, 'put');
      
      if (data) {
        rb.body(data.body, 'application/json');
      }

      // Add Authorization header
      rb.header('Authorization', `Bearer ${this.getJwtToken()}`);
    
      return http.request(
        rb.build({ responseType: 'json', accept: '*/*', context })
      ).pipe(
        filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
        map((r: HttpResponse<any>) => {
          return r as StrictHttpResponse<{
          }>;
        })
      );
    }

    delete(http: HttpClient, rootUrl: string, id: number, context?: HttpContext): Observable<StrictHttpResponse<{
    }>> {
      const rb = new RequestBuilder(rootUrl, `${this.urlPaths.delete}\/${id}`, 'delete');
      if (id) {
        rb.path('id', id, {});
      }
    
      // Add Authorization header
      rb.header('Authorization', `Bearer ${this.getJwtToken()}`);
    
      return http.request(
        rb.build({ responseType: 'json', accept: '*/*', context })
      ).pipe(
        filter((r: any): r is HttpResponse<any> => r instanceof HttpResponse),
        map((r: HttpResponse<any>) => {
          return r as StrictHttpResponse<{
          }>;
        })
      );
    }
    
    getJwtToken(){
        return localStorage.getItem('jwt_token') as string;
    }

}


export interface UrlPath{
    get: string;
    create: string;
    update: string;
    delete: string;
}