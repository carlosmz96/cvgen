import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CurriculumListPageComponent } from './curriculum-list-page.component';

describe('CurriculumListPageComponent', () => {
  let component: CurriculumListPageComponent;
  let fixture: ComponentFixture<CurriculumListPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CurriculumListPageComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CurriculumListPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
