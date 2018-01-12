import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Storage } from '@ionic/storage';

/*
  Generated class for the PastData provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class PastData {

  constructor(public http: Http, public storage: Storage) {
    console.log('Hello PastData Provider');
  }

  getData() {
    return this.storage.get('pasts'); 

  }
 
  save(data){
    let newData = JSON.stringify(data);
    this.storage.set('pasts', newData);
  }

}
