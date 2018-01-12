import { Component } from '@angular/core';
import { NavController, NavParams, ModalController, AlertController, ViewController } from 'ionic-angular';
import { NewExamPage } from '../new-exam/new-exam';
import { ExamDetailPage } from '../exam-detail/exam-detail';
import { EditExamPage } from '../edit-exam/edit-exam';
import { Examp } from '../../providers/examp';

/*
  Generated class for the Exams page.

  See http://ionicframework.com/docs/v2/components/#navigation for more info on
  Ionic pages and navigation.
*/
@Component({
  selector: 'page-exams',
  templateUrl: 'exam.html'
})
export class ExamsPage {

	 newexampage = NewExamPage;
  examdetailpage = ExamDetailPage;
  editexampage = EditExamPage;

  public exams = [];

  constructor(public navCtrl: NavController, public navParams: NavParams, public alertCtrl: AlertController, public modalCtrl: ModalController, public examService: Examp) {}

  addExam(){
 
    let addModal = this.modalCtrl.create(this.newexampage)
 
    addModal.onDidDismiss((exam) => {
 
          if(exam){
            this.saveExam(exam);
          }
 
    });
 
    addModal.present();
 
  }
  editExam(exam){

 
    
    this.navCtrl.push(this.editexampage, {
    exam: exam
    });
 
 
   
 
  }

  saveExam(exam){
    this.examService.createExam(exam);
  }
  updateExam(exam){
     this.examService.updateExam({
              _id: exam._id,
              _rev: exam._rev,
              course: exam.course,
              lecturer: exam.lecturer,
              invigilators: exam.invigilators,
              date:exam.date,
             
            });
  }
 
  viewExam(exam){
    this.navCtrl.push(ExamDetailPage, {
    exam: exam
    });
  //console.log(assignment);
}


    deleteExam(exam){
 
         this.examService.deleteExam(exam);

         let alert = this.alertCtrl.create({
      title: 'Success',
      subTitle: 'Exam Deleted.',
      buttons: ['OK']
    });
    alert.present();
      
      this.examService.getExam().then((data) => {
      this.exams = data;
    });
        
    }

  //newassPage(){
    //this.navCtrl.push(this.newasspage);
  //}
  examdetailPage(){
    this.navCtrl.push(this.examdetailpage);
  }

  ionViewDidLoad() {
    console.log('ionViewDidLoad ExamsPage');
    this.examService.getExam().then((data) => {
      this.exams = data;
    });
  }

}

