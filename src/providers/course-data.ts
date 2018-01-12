import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import { Storage } from '@ionic/storage';

/*
  Generated class for the CourseData provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class CourseData {

   constructor(public http: Http, public storage: Storage) {
    console.log('Hello Project Data Provider');
  }

  getData() {
   // return this.storage.get('courses'); 
    return this.storage.get('courses'); 

  }
 
  save(data){
    let newData = JSON.stringify(data);
    //this.storage.set('courses', newData);
    this.storage.set('courses', newData);
  }

  delete(data){
  this.storage.remove('courses');
  }

  update(data){
     let newData = JSON.stringify(data);
    this.storage.set('courses', newData);

  }
}
