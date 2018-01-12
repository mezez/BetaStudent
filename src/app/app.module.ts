import { NgModule, ErrorHandler,  ViewContainerRef } from '@angular/core';
import { IonicApp, IonicModule, IonicErrorHandler } from 'ionic-angular';
import { MyApp } from './app.component';
import { Storage } from '@ionic/storage';
import { Data } from '../providers/data';
import { ProjectData } from '../providers/projectdata';
import { PastData } from '../providers/past-data';
import { Coursesp } from '../providers/coursesp';
import { Timetablep } from '../providers/timetablep';


import { WelcomePage } from '../pages/welcome/welcome';
import { CoursesPage } from '../pages/courses/courses';
import { ProjectsPage } from '../pages/projects/projects';
import { EditProjectPage } from '../pages/edit-project/edit-project';
import { AssignmentsPage } from '../pages/assignments/assignments';
import { NewAssPage } from '../pages/new-ass/new-ass';
import { NewTimetablePage } from '../pages/new-timetable/new-timetable';
import { AssDetailPage } from '../pages/ass-detail/ass-detail';
import { TimetableDetailPage } from '../pages/timetable-detail/timetable-detail';
import { ProjectDetailPage } from '../pages/project-detail/project-detail';
import { EditAssPage } from '../pages/edit-ass/edit-ass';
import { NewProjectPage } from '../pages/new-project/new-project';
import { TimetablesPage } from '../pages/timetables/timetables';
import { EditTimetablePage } from '../pages/edit-timetable/edit-timetable';
import { ExamsPage } from '../pages/exam/exam';
import { EditExamPage } from '../pages/edit-exam/edit-exam';
import { NewExamPage } from '../pages/new-exam/new-exam';
import { ExamDetailPage } from '../pages/exam-detail/exam-detail';
import { PastquestionsPage } from '../pages/pastquestions/pastquestions';
import { NewPastPage } from '../pages/new-past/new-past';
import { PastDetailPage } from '../pages/past-detail/past-detail';
import { NewCoursePage } from '../pages/new-course/new-course';
import { CourseDetailPage } from '../pages/course-detail/course-detail';
import { EditCoursePage } from '../pages/edit-course/edit-course';
import { FavlinksPage } from '../pages/favlinks/favlinks';
import { NewFavlinkPage } from '../pages/new-favlink/new-favlink';
import { FavlinkDetailPage } from '../pages/favlink-detail/favlink-detail';
import { EditFavlinkPage } from '../pages/edit-favlink/edit-favlink';
import { Pouch } from '../providers/pouch';
import { Favlinksp } from '../providers/favlinksp';
import { Examp } from '../providers/examp';
import { Pastimage } from '../providers/pastimage';


import { Page1 } from '../pages/page1/page1';
import { Page2 } from '../pages/page2/page2';

@NgModule({
  declarations: [
    MyApp,

    WelcomePage,
    TimetablesPage,
    ExamsPage,
    CoursesPage,
    NewCoursePage,
    CourseDetailPage,
    ExamDetailPage,
    NewExamPage,
    EditCoursePage,
    EditExamPage,
    ProjectsPage,
    EditTimetablePage,
    EditProjectPage,
    AssignmentsPage,
    NewAssPage,
    NewTimetablePage,
    AssDetailPage,
    TimetableDetailPage,
    ProjectDetailPage,
    EditAssPage,
    NewProjectPage,
    PastquestionsPage,
    NewPastPage,
    PastDetailPage,
    FavlinksPage,
    NewFavlinkPage,
    EditFavlinkPage,
    FavlinkDetailPage,

    Page1
  ],
  imports: [
    IonicModule.forRoot(MyApp)
  ],
  bootstrap: [IonicApp],
  entryComponents: [
    MyApp,

    WelcomePage,
    TimetablesPage,
    ExamsPage,
    ExamDetailPage,
    NewExamPage,
    NewTimetablePage,
    TimetableDetailPage,
    CoursesPage,
    NewCoursePage,
    CourseDetailPage,
    EditCoursePage,
    EditTimetablePage,
    EditExamPage,
    ProjectsPage,
    EditProjectPage,
    AssignmentsPage,
    NewAssPage,
    NewProjectPage,
    NewPastPage,
    AssDetailPage,
    ProjectDetailPage,
    PastquestionsPage,
    EditAssPage,
     FavlinksPage,
    NewFavlinkPage,
    EditFavlinkPage,
    FavlinkDetailPage,

    Page1
  ],
  providers: [{provide: ErrorHandler, useClass: IonicErrorHandler}, Storage, Data, ProjectData, PastData, Coursesp, Timetablep,Examp, Pouch, Favlinksp]
})
export class AppModule {}
