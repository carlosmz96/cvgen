import { Certification } from "./Certification";
import { Education } from "./Education";
import { Experience } from "./Experience";
import { LanguageSkill } from "./LanguageSkill";
import { Skill } from "./Skill";

export interface Curriculum {
  id?: number;
  title: string;
  summary?: string;
  theme: string;
  language: string;
  experiences: Experience[];
  educations: Education[];
  skills: Skill[];
  certifications: Certification[];
  languageSkills: LanguageSkill[];
}
