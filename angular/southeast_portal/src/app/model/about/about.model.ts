import { AchievementModel } from "./achievement.model";
import { ImagesModel } from "./images.model";
import { MissionAndValuesModel } from "./mission.model";

export interface AboutModel{
    id?: number;
    universityName?: string;
    title?: string;
    shortTitle?: string;
    details?: string;
    establishYear?: string;
    thumbnailUrl?: string;
    achievementImageUrl?: string;
    historyImageUrl?: string;
    historyDetails?: string;
    achievements?: AchievementModel[];
    missionAndValues?: MissionAndValuesModel[];
    images?: ImagesModel;
}