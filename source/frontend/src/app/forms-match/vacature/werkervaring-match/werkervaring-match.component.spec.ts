import {ComponentFixture, TestBed} from '@angular/core/testing';

import {WerkervaringMatchComponent} from './werkervaring-match.component';

describe('WerkervaringMatchComponent', () => {
  let component: WerkervaringMatchComponent;
  let fixture: ComponentFixture<WerkervaringMatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [WerkervaringMatchComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(WerkervaringMatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
