import {ComponentFixture, TestBed} from '@angular/core/testing';

import {PostbusadresBuitenlandMatchDetailedComponent} from './postbusadres-buitenland-match-detailed.component';

describe('PostbusadresBuitenlandMatchDetailedComponent', () => {
  let component: PostbusadresBuitenlandMatchDetailedComponent;
  let fixture: ComponentFixture<PostbusadresBuitenlandMatchDetailedComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [PostbusadresBuitenlandMatchDetailedComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PostbusadresBuitenlandMatchDetailedComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
