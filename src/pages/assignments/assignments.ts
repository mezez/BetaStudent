import { Component } from '@angular/core';
import { NavController, NavParams, AlertController, ModalController} from 'ionic-angular';
import { StatusBar, Splashscreen, SQLite } from 'ionic-native';
import { NewAssPage } from '../new-ass/new-ass';
import { AssDetailPage } from '../ass-detail/ass-detail';
import { EditAssPage } from '../edit-ass/edit-ass';
import { MyApp } from '../../app/app.component';
//import { Data } from '../../providers/data';
import { Pouch } from '../../providers/pouch';



/*
  Generated class for the Assignments page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-assignments',
  templateUrl: 'assignments.html'
})
export class AssignmentsPage {
  newasspage = NewAssPage;
  assdetailpage = AssDetailPage;
  editasspage = EditAssPage;

  public assignments = [];

  data: any;
  db: any;
  remote: any;


  constructor(public navCtrl: NavController, public navParams: NavParams,  public alertCtrl: AlertController, public modalCtrl: ModalController, public pouchService: Pouch) {
    
      
  }


  




    addAss(){
 
    let addModal = this.modalCtrl.create(this.newasspage)
 
    addModal.onDidDismiss((assignment) => {
 
          if(assignment){
            this.saveAss(assignment);
          }
 
    });
 
    addModal.present();
 
  }
  editAss(assignment){

 
    //let editModal = this.modalCtrl.create(this.editasspage, assignment);
    this.navCtrl.push(this.editasspage, {
    assignment: assignment
    });
 
    //editModal.onDidDismiss((assignment) => {
 
          //if(assignment){
            //this.updateAss(assignment);
          //}
 
    //});
 
    //editModal.present();
 
   
 
  }

  saveAss(assignment){
     this.pouchService.createAss({title: assignment.title ,description: assignment.description, date: assignment.date});
  }
  updateAss(assignment){
     this.pouchService.updateAss({
              _id: assignment._id,
              _rev: assignment._rev,
              title: assignment.title,
              description: assignment.description,
              date: assignment.date
            });
  }
 
  viewAss(assignment){
    this.navCtrl.push(AssDetailPage, {
    assignment: assignment
    });
 
}



    deleteAss(assignment){
 
       this.pouchService.deleteAss(assignment);
  

         let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Assignment Deleted.',
      buttons: ['OK']
    });
    alert.present();
    
         this.pouchService.getAss().then((data) => {
      this.assignments = data;
    });
        
    }

  //newassPage(){
    //this.navCtrl.push(this.newasspage);
  //}
  assdetailPage(){
    this.navCtrl.push(this.assdetailpage);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad AssignmentsPage');
    this.pouchService.getAss().then((data) => {
      this.assignments = data;
    });
  }

}
