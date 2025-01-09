from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from typing import Dict,List
import joblib
from feature_engineer import FeatureEngineer
import pandas as pd
import logging
##import openai


""""
data = {
    "segment": "SMALL_BUSINESSES",
    "role": "EIO",
    "organizations": 3,
    "currentMethod": "SMS",
    "mobileApp": True,
    "signatures": {
        "common": {
            "SMS": 3,
            "PAY_CONTROL": 10,
            "QS_TOKEN": 3,
            "QS_MOBILE": 4
        },
        "special": {
            "SMS": 3,
            "PAY_CONTROL": 10,
            "QS_TOKEN": 3,
            "QS_MOBILE": 4
        }
    },
    "availableMethods": ["SMS", "PAY_CONTROL"],
    "reports": {
        "SMS": 0,
        "PAY_CONTROL": 0,
        "QS_TOKEN": 0,
        "QS_MOBILE": 0
    },
    "context": {
        "docType": "PAYMENT_ORDER",
        "transactionAmount": "LOW",
        "deviceType": "MOBILE",
        "urgency": "HIGH",
        "bankSystemIssues": "NONE",
        "clientTypeIssues": "CONNECTION",
        "balanceOpportunity": "YES",
        "userLocation": "BUILDING"
    }
}

def parse_data(data: InputData):
    return {
        "segment": data["segment"],
        "role": data["role"],
        "organizations": data["organizations"],
        "currentMethod": data["currentMethod"],
        "mobileApp": data["mobileApp"],
        
        "available_SMS": 1 if "SMS" in data["availableMethods"] else 0,
        "available_PAY_CONTROL": 1 if "PAY_CONTROL" in data["availableMethods"] else 0,
        "available_QS_TOKEN": 1 if "QS_TOKEN" in data["availableMethods"] else 0,
        "available_QS_MOBILE": 1 if "QS_MOBILE" in data["availableMethods"] else 0,
         
        "report_SMS": data["reports"].get("SMS", 0),
        "report_PAY_CONTROL": data["reports"].get("PAY_CONTROL", 0),
        "report_QS_TOKEN": data["reports"].get("QS_TOKEN", 0),
        "report_QS_MOBILE": data["reports"].get("QS_MOBILE", 0),
        
        "common_SMS_signature": data["signatures"]["common"].get("SMS", 0),
        "common_PAY_CONTROL_signature": data["signatures"]["common"].get("PAY_CONTROL", 0),
        "common_QS_TOKEN_signature": data["signatures"]["common"].get("QS_TOKEN", 0),
        "common_QS_MOBILE_signature": data["signatures"]["common"].get("QS_MOBILE", 0),
        "special_SMS_signature": data["signatures"]["special"].get("SMS", 0),
        "special_PAY_CONTROL_signature": data["signatures"]["special"].get("PAY_CONTROL", 0),
        "special_QS_TOKEN_signature": data["signatures"]["special"].get("QS_TOKEN", 0),
        "special_QS_MOBILE_signature": data["signatures"]["special"].get("QS_MOBILE", 0),

        "context_docType": data["context"]["docType"],
        "context_transactionAmount": data["context"]["transactionAmount"],
        "context_deviceType": data["context"]["deviceType"],
        "context_urgency": data["context"]["urgency"],
        "context_bankSystemIssues": data["context"]["bankSystemIssues"],
        "context_clientTypeIssues": data["context"]["clientTypeIssues"],
        "context_balanceOpportunity": data["context"]["balanceOpportunity"],
        "context_userLocation": data["context"]["userLocation"],
    }
"""

class Signatures(BaseModel):
    common: Dict[str, int]
    special: Dict[str, int]


class Context(BaseModel):
    docType: str
    transactionAmount: str
    deviceType: str
    urgency: str
    bankSystemIssues: str
    clientTypeIssues: str
    balanceOpportunity: str
    userLocation: str


class InputData(BaseModel):
    segment: str
    role: str
    organizations: int
    currentMethod: str
    mobileApp: bool
    signatures: Signatures
    availableMethods: List[str]
    reports: Dict[str, int]
    context: Context

def parse_data(data: InputData):
    return {
        "segment": data.segment,
        "role": data.role,
        "organizations": data.organizations,
        "currentMethod": data.currentMethod,
        "mobileApp": data.mobileApp,

        "available_SMS": 1 if "SMS" in data.availableMethods else 0,
        "available_PAY_CONTROL": 1 if "PAY_CONTROL" in data.availableMethods else 0,
        "available_QS_TOKEN": 1 if "QS_TOKEN" in data.availableMethods else 0,
        "available_QS_MOBILE": 1 if "QS_MOBILE" in data.availableMethods else 0,

        "report_SMS": data.reports.get("SMS", 0),
        "report_PAY_CONTROL": data.reports.get("PAY_CONTROL", 0),
        "report_QS_TOKEN": data.reports.get("QS_TOKEN", 0),
        "report_QS_MOBILE": data.reports.get("QS_MOBILE", 0),

        "common_SMS_signature": data.signatures.common.get("SMS", 0),
        "common_PAY_CONTROL_signature": data.signatures.common.get("PAY_CONTROL", 0),
        "common_QS_TOKEN_signature": data.signatures.common.get("QS_TOKEN", 0),
        "common_QS_MOBILE_signature": data.signatures.common.get("QS_MOBILE", 0),
        "special_SMS_signature": data.signatures.special.get("SMS", 0),
        "special_PAY_CONTROL_signature": data.signatures.special.get("PAY_CONTROL", 0),
        "special_QS_TOKEN_signature": data.signatures.special.get("QS_TOKEN", 0),
        "special_QS_MOBILE_signature": data.signatures.special.get("QS_MOBILE", 0),

        "context_docType": data.context.docType,
        "context_transactionAmount": data.context.transactionAmount,
        "context_deviceType": data.context.deviceType,
        "context_urgency": data.context.urgency,
        "context_bankSystemIssues": data.context.bankSystemIssues,
        "context_clientTypeIssues": data.context.clientTypeIssues,
        "context_balanceOpportunity": data.context.balanceOpportunity,
        "context_userLocation": data.context.userLocation,
    }


app = FastAPI()


try:
    model = joblib.load("model.pkl")
except FileNotFoundError:
    raise RuntimeError("Module not found!")

# @app.get("/recommendation")
# async def get_recommedation(data:InputData):
#     try:
#         clean_data =parse_data(data)
#         prediction = model.predict(pd.DataFrame([clean_data]))

#         return {"prediction": prediction}
#     except Exception as e:
#         raise HTTPException(status_code=500,detail=f"Error processing request: {str(e)}")


def return_asnwer(prediction):
    if prediction[0]==0:
        return "SMS"
    elif prediction[0]==1:
        return "QS_MOBILE"
    elif prediction[0]==2:
        return "PAY_CONTROL"
    elif prediction[0]==3:
        return "QS_TOKEN"


logging.basicConfig(level=logging.INFO)


@app.get("/recommendation")
async def get_recommendation(data: InputData):
    logging.info("Received data for prediction: %s", data)
    try:
        clean_data = parse_data(data)
        prediction = model.predict(pd.DataFrame([clean_data]))
        return {"recommendation": return_asnwer(prediction.tolist())}
    except Exception as e:
        logging.error("Error processing request: %s", str(e))
        raise HTTPException(status_code=500, detail=f"Error processing request: {str(e)}")