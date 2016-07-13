package mktvsmart.screen.dataconvert.model;

public class DataConvertUpdateModel
{
    private int customerId;
    private int dataLen;
    private int modelId;
    private int versionId;

    public int GetCustomerId() {
        return this.customerId;
    }

    public int GetDataLen() {
        return this.dataLen;
    }

    public int GetModelId() {
        return this.modelId;
    }

    public int GetVersionId() {
        return this.versionId;
    }

    public void SetCustomerId(final int customerId) {
        this.customerId = customerId;
    }

    public void SetDataLen(final int dataLen) {
        this.dataLen = dataLen;
    }

    public void SetModelId(final int modelId) {
        this.modelId = modelId;
    }

    public void SetVersionId(final int versionId) {
        this.versionId = versionId;
    }
}
