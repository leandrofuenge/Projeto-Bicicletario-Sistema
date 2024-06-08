import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://192.168.1.4:9090/api/login'; // URL do endpoint de login

  constructor(private http: HttpClient) { }

  fazerLogin(cpf: string, senha: string): Observable<any> {
    const headers = new HttpHeaders({ 'Content-Type': 'application/json' });
    const body = { cpf, senha}; 

    return this.http.post(this.apiUrl, body, { headers });
  }

}
