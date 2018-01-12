import { Injectable } from '@angular/core';
import { Http } from '@angular/http';
import 'rxjs/add/operator/map';
import PouchDB from 'pouchdb';

/*
  Generated class for the Coursesp provider.

  See https://angular.io/docs/ts/latest/guide/dependency-injection.html
  for more info on providers and Angular 2 DI.
*/
@Injectable()
export class Coursesp {

	data: any;
  db: any;
  remote: any;

  constructor(public http: Http) {
    console.log('Hello Coursesp Provider');
    this.db = new PouchDB('coursesp');
 
    this.remote = 'http://localhost:5984/courses';
 
    let options = {
      live: true,
      retry: true,
      continuous: true
    };
 
    this.db.sync(this.remote, options);
  }

  getCourse() {
 
  if (this.data) {
    return Promise.resolve(this.data);
  }
 
  return new Promise(resolve => {
 
    this.db.allDocs({
 
      include_docs: true
 
    }).then((result) => {
 
      this.data = [];
 
      let docs = result.rows.map((row) => {
        this.data.push(row.doc);
      });
 
      resolve(this.data);
 
      this.db.changes({live: true, since: 'now', include_docs: true}).on('change', (change) => {
        this.handleChange(change);
      });
 
    }).catch((error) => {
 
      console.log(error);
 
    }); 
 
  });
 
}

handleChange(change){
 
  let changedDoc = null;
  let changedIndex = null;
 
  this.data.forEach((doc, index) => {
 
    if(doc._id === change.id){
      changedDoc = doc;
      changedIndex = index;
    }
 
  });
 
  //A document was deleted
  if(change.deleted){
    this.data.splice(changedIndex, 1);
  } 
  else {
 
    //A document was updated
    if(changedDoc){
      this.data[changedIndex] = change.doc;
    } 
 
    //A document was added
    else {
      this.data.push(change.doc); 
    }
 
  }
 
}

createCourse(course){
  this.db.post(course);
}
 
updateCourse(course){
  this.db.put(course).catch((err) => {
    console.log(err);
  });
}
 
deleteCourse(course){
  this.db.remove(course).catch((err) => {
    console.log(err);
  });
}

}
